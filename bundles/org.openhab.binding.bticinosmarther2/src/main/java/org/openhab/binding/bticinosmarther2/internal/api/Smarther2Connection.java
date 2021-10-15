/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.bticinosmarther2.internal.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.FormContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.util.Fields;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.openhab.binding.bticinosmarther2.internal.api.json.*;
import org.openhab.binding.bticinosmarther2.internal.api.json.homestatus.HomeStatusResponse;
import org.openhab.binding.bticinosmarther2.internal.exceptions.Smarther2CommException;
import org.openhab.binding.bticinosmarther2.internal.exceptions.Smarther2LoginException;
import org.openhab.core.io.net.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * The {@link Smarther2Connection} Manage connection to Mitsubishi Cloud (MelCloud).
 *
 * @author Luca Calcaterra - Initial Contribution
 * @author Pauli Anttila - Refactoring
 * @author Wietse van Buitenen - Return all devices, added heatpump device
 */
public class Smarther2Connection {

    private static final String LOGIN_URL = "https://app.netatmo.net/oauth2/token";
    private static final String HOMES_DATA_URL = "https://app.netatmo.net/api/homesdata";
    private static final String HOME_STATUS_URL = "https://app.netatmo.net/syncapi/v1/homestatus";
    private static final int TIMEOUT_MILLISECONDS = 10000;

    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

    private final Logger logger = LoggerFactory.getLogger(Smarther2Connection.class);

    private boolean isConnected = false;
    private String refreshToken;
    private String token;

    public void login(String username, String password, boolean useRefreshToken)
            throws Smarther2CommException, Smarther2LoginException {
        Fields fields = new Fields();
        if (useRefreshToken && refreshToken != null) {
            fields.put("refresh_token", refreshToken);
            fields.put("grant_type", "refresh_token");
        } else {
            fields.put("username", username);
            fields.put("password", password);
            fields.put("grant_type", "password");
        }
        fields.put("app_version", "2.3.2.0");
        fields.put("scope", "write_thermostat read_thermostat read_smarther write_smarther magellan_scopes all_scopes");
        fields.put("client_secret", "a6e15c52b06364b3251b2c5ae3dd8521");
        fields.put("client_id", "na_client_android_magellan");
        try {
            HttpClient client = new HttpClient(new SslContextFactory(true));
            client.start();
            ContentResponse response = client.newRequest(LOGIN_URL).agent("Jetty HTTP client")
                    .version(HttpVersion.HTTP_1_1).method(HttpMethod.POST)
                    .timeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS).content(new FormContentProvider(fields))
                    .send();
            String loginResponse = response.getContentAsString();
            logger.debug("Login response: {}", loginResponse);
            LoginResponse resp = gson.fromJson(response.getContentAsString(), LoginResponse.class);
            token = resp.getAccessToken();
            refreshToken = resp.getRefreshToken();
            logger.debug("Login token: {}", token);
            logger.debug("Login refresh token: {}", refreshToken);
            setConnected(true);
            client.stop();
        } catch (IOException | JsonSyntaxException e) {
            throw new Smarther2LoginException(String.format("Login error, reason: %s", e.getMessage()), e);
        } catch (Exception e) {
            throw new Smarther2CommException(String.format("Login generic error, reason: %s", e.getMessage()), e);
        }
    }

    public void loginWithRefreshToken() throws Smarther2CommException, Smarther2LoginException {
        login(null, null, true);
    }

    public synchronized boolean isConnected() {
        return isConnected;
    }

    private synchronized void setConnected(boolean state) {
        isConnected = state;
    }

    private Properties getHeaderProperties() {
        Properties headers = new Properties();
        headers.put("Authorization", "Bearer " + token);
        return headers;
    }

    private void assertConnected() throws Smarther2CommException {
        if (!isConnected) {
            throw new Smarther2CommException("Not connected to BrinkHome");
        }
    }

    public List<Smarther2Device> fetchDeviceList() throws Smarther2CommException {
        assertConnected();
        HomesDataRequest homesDataRequest = new HomesDataRequest();
        homesDataRequest.setAppType("app_magellan");
        homesDataRequest.setAppVersion("2.3.2.0");
        homesDataRequest.setDeviceTypes(Arrays.asList("NAPlug", "BNS", "NLG", "NBG", "TPSG", "NLE", "OTH"));
        homesDataRequest.setSyncMeasurements(true);
        String content = gson.toJson(homesDataRequest, HomesDataRequest.class);
        InputStream data = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        try {
            String response = HttpUtil.executeUrl("POST", HOMES_DATA_URL, getHeaderProperties(), data,
                    "application/json", TIMEOUT_MILLISECONDS);
            logger.debug("Device list response: {}", response);
            HomesDataResponse devicesArray = gson.fromJson(response, HomesDataResponse.class);
            List<Smarther2Device> devices = new ArrayList<>();
            if (devicesArray.getBody() != null && devicesArray.getBody().getHomes() != null)
                for (Home home : devicesArray.getBody().getHomes()) {
                    String homeId = home.getId();
                    if (home.getRooms() != null)
                        for (Room room : home.getRooms()) {
                            String roomId = room.getId();
                            String roomName = room.getName();
                            if (room.getModuleIds() != null)
                                for (String deviceId : room.getModuleIds()) {
                                    Smarther2Device smarther2Device = new Smarther2Device();
                                    smarther2Device.setHomeId(homeId);
                                    smarther2Device.setDeviceId(deviceId);
                                    smarther2Device.setRoomId(roomId);
                                    smarther2Device.setRoomName(roomName);
                                    devices.add(smarther2Device);
                                }
                        }
                }
            logger.debug("Found {} devices", devices.size());
            return devices;
        } catch (IOException | JsonSyntaxException e) {
            setConnected(false);
            throw new Smarther2CommException("Error occurred during device list poll", e);
        }
    }

    public Smarther2DeviceStatus fetchDeviceStatus(String homeId, String roomId) throws Smarther2CommException {
        assertConnected();
        HomesDataRequest homesDataRequest = new HomesDataRequest();
        homesDataRequest.setAppType("app_magellan");
        homesDataRequest.setAppVersion("2.3.2.0");
        homesDataRequest.setDeviceTypes(Arrays.asList("NAPlug", "BNS", "NLG", "NBG", "TPSG", "NLE", "OTH"));
        homesDataRequest.setHomeId(homeId);
        String content = gson.toJson(homesDataRequest, HomesDataRequest.class);
        InputStream data = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        try {
            String response = HttpUtil.executeUrl("POST", HOME_STATUS_URL, getHeaderProperties(), data,
                    "application/json", TIMEOUT_MILLISECONDS);
            logger.debug("Device list response: {}", response);
            HomeStatusResponse homeStatusResponse = gson.fromJson(response, HomeStatusResponse.class);
            Smarther2DeviceStatus smarther2DeviceStatus = new Smarther2DeviceStatus();
            for (org.openhab.binding.bticinosmarther2.internal.api.json.homestatus.Room room : homeStatusResponse
                    .getBody().getHome().getRooms()) {
                if (room.getId().equals(roomId)) {
                    smarther2DeviceStatus.setHumidity(room.getHumidity());
                    smarther2DeviceStatus.setTemperature(room.getThermMeasuredTemperature());
                }
            }
            return smarther2DeviceStatus;
        } catch (IOException | JsonSyntaxException e) {
            setConnected(false);
            throw new Smarther2CommException("Error occurred during device list poll", e);
        }
    }
}
