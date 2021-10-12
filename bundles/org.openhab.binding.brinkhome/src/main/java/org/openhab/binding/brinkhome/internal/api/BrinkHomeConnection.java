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
package org.openhab.binding.brinkhome.internal.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.openhab.binding.brinkhome.internal.api.json.*;
import org.openhab.binding.brinkhome.internal.exceptions.BrinkHomeCommException;
import org.openhab.core.io.net.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.*;

/**
 * The {@link BrinkHomeConnection} Manage connection to Mitsubishi Cloud (MelCloud).
 *
 * @author Luca Calcaterra - Initial Contribution
 * @author Pauli Anttila - Refactoring
 * @author Wietse van Buitenen - Return all devices, added heatpump device
 */
public class BrinkHomeConnection {

    private static final String LOGIN_URL = "https://www.brink-home.com/portal/api/portal/UserLogon";
    private static final String SYSTEM_LIST_URL = "https://www.brink-home.com/portal/api/portal/GetSystemList";
    private static final String APP_GUI_DESCRIPTION_URL = "https://www.brink-home.com/portal/api/portal/GetAppGuiDescriptionForGateway";
    private static final String READ_PARAMETER_VALUES_URL = "https://www.brink-home.com/portal/api/portal/GetParameterValues";
    private static final String WRITE_PARAMETER_VALUES_URL = "https://www.brink-home.com/portal/api/portal/WriteParameterValuesAsync";

    private static final int TIMEOUT_MILLISECONDS = 10000;

    // Gson objects are safe to share across threads and are somewhat expensive to construct. Use a single instance.
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

    private final Logger logger = LoggerFactory.getLogger(BrinkHomeConnection.class);

    private boolean isConnected = false;
    private String cookie;

    public void login(String username, String password) throws BrinkHomeCommException, BrinkHomeCommException {
        cookie = null;
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(username);
        loginRequest.setPassword(password);
        loginRequest.setRememberMe(false);
        loginRequest.setRemoteIpAddress(null);
        loginRequest.setUserAgent(null);
        loginRequest.setAppVersion("1.00.24");
        loginRequest.setWebsiteVersion(0);
        loginRequest.setWebApiVersion(2);
        loginRequest.setUserRole(0);
        try {
            HttpClient client = new HttpClient(new SslContextFactory(true));
            client.start();
            ContentResponse response = client.newRequest(LOGIN_URL).agent("Jetty HTTP client")
                    .version(HttpVersion.HTTP_1_1).method(HttpMethod.POST)
                    .timeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .content(new StringContentProvider(gson.toJson(loginRequest))).send();
            String loginResponse = response.getContentAsString();
            logger.debug("Login response: {}", loginResponse);
            LoginResponse resp = gson.fromJson(loginResponse, LoginResponse.class);
            cookie = response.getHeaders().get("Set-Cookie");
            logger.debug("Login cookie: {}", cookie);
            setConnected(true);
            client.stop();
        } catch (IOException | JsonSyntaxException e) {
            throw new BrinkHomeCommException(String.format("Login error, reason: %s", e.getMessage()), e);
        } catch (Exception e) {
            throw new BrinkHomeCommException(String.format("Login generic error, reason: %s", e.getMessage()), e);
        }
    }

    public synchronized boolean isConnected() {
        return isConnected;
    }

    private synchronized void setConnected(boolean state) {
        isConnected = state;
    }

    private Properties getHeaderProperties() {
        Properties headers = new Properties();
        headers.put("Cookie", cookie);
        return headers;
    }

    private void assertConnected() throws BrinkHomeCommException {
        if (!isConnected) {
            throw new BrinkHomeCommException("Not connected to BrinkHome");
        }
    }

    public List<SystemListResponse> fetchDeviceList() throws BrinkHomeCommException {
        assertConnected();
        try {
            String response = HttpUtil.executeUrl("GET", SYSTEM_LIST_URL, getHeaderProperties(), null, null,
                    TIMEOUT_MILLISECONDS);
            logger.debug("Device list response: {}", response);
            SystemListResponse[] devicesArray = gson.fromJson(response, SystemListResponse[].class);
            List<SystemListResponse> devices = new ArrayList<>(Arrays.asList(devicesArray));
            logger.debug("Found {} devices", devices.size());
            return devices;
        } catch (IOException | JsonSyntaxException e) {
            setConnected(false);
            throw new BrinkHomeCommException("Error occurred during device list poll", e);
        }
    }

    public void sendVentilationCommand(Integer systemId, Integer gatewayId, Integer speed)
            throws BrinkHomeCommException {
        WriteParameterValue writeParameterValue1 = new WriteParameterValue();
        writeParameterValue1.setValueId(242083);
        writeParameterValue1.setParameterId(0);
        writeParameterValue1.setParameterName(null);
        writeParameterValue1.setValue(speed.toString());
        WriteParameterValue writeParameterValue2 = new WriteParameterValue();
        writeParameterValue2.setValueId(242084);
        writeParameterValue2.setParameterId(0);
        writeParameterValue2.setParameterName(null);
        writeParameterValue2.setValue("1");
        List<WriteParameterValue> writeParameterValues = new ArrayList<>(
                Arrays.asList(writeParameterValue2, writeParameterValue1));
        List<Integer> dependentReadValues = new ArrayList<>(Collections.singletonList(242083));
        WriteParameterValueRequest writeParameterValueRequest = new WriteParameterValueRequest();
        writeParameterValueRequest.setGatewayId(gatewayId);
        writeParameterValueRequest.setSystemId(systemId);
        writeParameterValueRequest.setGuiId(0);
        writeParameterValueRequest.setWriteParameterValues(writeParameterValues);
        writeParameterValueRequest.setDependendReadValuesAfterWrite(dependentReadValues);
        String content = gson.toJson(writeParameterValueRequest, WriteParameterValueRequest.class);
        logger.debug("Sending heatpump device status: {}", content);
        InputStream data = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        try {
            String response = HttpUtil.executeUrl("POST", WRITE_PARAMETER_VALUES_URL, getHeaderProperties(), data,
                    "application/json", TIMEOUT_MILLISECONDS);
            logger.debug("Device heatpump status sending response: {}", response);
        } catch (IOException | JsonSyntaxException e) {
            setConnected(false);
            throw new BrinkHomeCommException("Error occurred during heatpump device command sending", e);
        }
    }

    public void sendOperationModeCommand(Integer systemId, Integer gatewayId, Integer operationMode)
            throws BrinkHomeCommException {
        WriteParameterValue writeParameterValue = new WriteParameterValue();
        writeParameterValue.setValueId(242084);
        writeParameterValue.setParameterId(0);
        writeParameterValue.setParameterName(null);
        writeParameterValue.setValue(operationMode.toString());
        List<WriteParameterValue> writeParameterValues = new ArrayList<>(
                Collections.singletonList(writeParameterValue));
        List<Integer> dependentReadValues = new ArrayList<>(Collections.singletonList(242083));
        WriteParameterValueRequest writeParameterValueRequest = new WriteParameterValueRequest();
        writeParameterValueRequest.setGatewayId(gatewayId);
        writeParameterValueRequest.setSystemId(systemId);
        writeParameterValueRequest.setGuiId(0);
        writeParameterValueRequest.setWriteParameterValues(writeParameterValues);
        writeParameterValueRequest.setDependendReadValuesAfterWrite(dependentReadValues);
        String content = gson.toJson(writeParameterValueRequest, WriteParameterValueRequest.class);
        logger.debug("Sending heatpump device status: {}", content);
        InputStream data = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        try {
            String response = HttpUtil.executeUrl("POST", WRITE_PARAMETER_VALUES_URL, getHeaderProperties(), data,
                    "application/json", TIMEOUT_MILLISECONDS);
            logger.debug("Device heatpump status sending response: {}", response);
        } catch (IOException | JsonSyntaxException e) {
            setConnected(false);
            throw new BrinkHomeCommException("Error occurred during heatpump device command sending", e);
        }
    }

    public ParameterValuesResponse fetchDeviceStatus(Integer systemId, Integer gatewayId)
            throws BrinkHomeCommException {
        assertConnected();
        ParameterValuesRequest parameterValuesRequest = new ParameterValuesRequest();
        parameterValuesRequest.setGatewayId(gatewayId);
        parameterValuesRequest.setSystemId(systemId);
        parameterValuesRequest.setGuiId(0);
        parameterValuesRequest.setSessionId(null);
        parameterValuesRequest.setGuiIdChanged(false);
        parameterValuesRequest.setValueIdList(Arrays.asList(242083, 242084, 242085, 242116, 242114));
        parameterValuesRequest.setLastAccess("0001-01-01T00:00:00");
        parameterValuesRequest.setSystemBusSamplingRateSec(0);
        parameterValuesRequest.setIsSubBundle(false);
        String content = gson.toJson(parameterValuesRequest, ParameterValuesRequest.class);
        InputStream data = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        try {
            String response = HttpUtil.executeUrl("POST", READ_PARAMETER_VALUES_URL, getHeaderProperties(), data,
                    "application/json", TIMEOUT_MILLISECONDS);
            logger.debug("Device list response: {}", response);
            ParameterValuesResponse parameterValues = gson.fromJson(response, ParameterValuesResponse.class);
            return parameterValues;
        } catch (IOException | JsonSyntaxException e) {
            setConnected(false);
            throw new BrinkHomeCommException("Error occurred during device list poll", e);
        }
    }
}
