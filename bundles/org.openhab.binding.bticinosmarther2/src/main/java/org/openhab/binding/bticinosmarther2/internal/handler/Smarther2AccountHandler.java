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
package org.openhab.binding.bticinosmarther2.internal.handler;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.openhab.binding.bticinosmarther2.internal.api.Smarther2Connection;
import org.openhab.binding.bticinosmarther2.internal.api.json.Smarther2Device;
import org.openhab.binding.bticinosmarther2.internal.api.json.Smarther2DeviceStatus;
import org.openhab.binding.bticinosmarther2.internal.config.AccountConfig;
import org.openhab.binding.bticinosmarther2.internal.discovery.Smarther2DiscoveryService;
import org.openhab.binding.bticinosmarther2.internal.exceptions.Smarther2CommException;
import org.openhab.binding.bticinosmarther2.internal.exceptions.Smarther2LoginException;
import org.openhab.core.thing.*;
import org.openhab.core.thing.binding.BaseBridgeHandler;
import org.openhab.core.thing.binding.ThingHandlerService;
import org.openhab.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link Smarther2AccountHandler} is the handler for Smarther2 API and connects it
 * to the webservice.
 *
 * @author Giacomo Agostini - Initial contribution
 */
public class Smarther2AccountHandler extends BaseBridgeHandler {
    private final Logger logger = LoggerFactory.getLogger(Smarther2AccountHandler.class);

    private Smarther2Connection connection;
    private ScheduledFuture<?> connectionCheckTask;
    private AccountConfig config;
    private boolean loginCredentialError;
    private ScheduledFuture<?> refreshTask;

    public Smarther2AccountHandler(Bridge bridge) {
        super(bridge);
    }

    @Override
    public Collection<Class<? extends ThingHandlerService>> getServices() {
        return Collections.singleton(Smarther2DiscoveryService.class);
    }

    @Override
    public void initialize() {
        logger.debug("Initializing Smarther2 account handler.");
        config = getConfigAs(AccountConfig.class);
        connection = new Smarther2Connection();
        loginCredentialError = false;
        startConnectionCheck();
    }

    @Override
    public void dispose() {
        logger.debug("Running dispose()");
        stopConnectionCheck();
        connection = null;
        config = null;
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
    }

    private void startAutomaticRefresh() {
        if (refreshTask == null || refreshTask.isCancelled()) {
            refreshTask = scheduler.scheduleWithFixedDelay(this::loginWithRefreshToken, 7200, 7200, TimeUnit.SECONDS);
        }
    }

    public ThingUID getID() {
        return getThing().getUID();
    }

    private void connect() throws Smarther2CommException, Smarther2LoginException {
        if (loginCredentialError) {
            throw new Smarther2LoginException("Connection to Smarther2 can't be opened because of wrong credentials");
        }
        logger.debug("Initializing connection to Smarther2");
        updateStatus(ThingStatus.OFFLINE);
        try {
            connection.login(config.username, config.password, false);
            updateStatus(ThingStatus.ONLINE);
        } catch (Smarther2CommException e) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, e.getMessage());
            throw e;
        }
        startAutomaticRefresh();
    }

    private void loginWithRefreshToken() {
        try {
            connection.loginWithRefreshToken();
        } catch (Exception e) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, e.getMessage());
            logger.warn("Connection to Smarther2 down due to login error, reason: {}.", e.getMessage());
        }
    }

    private synchronized void connectIfNotConnected() throws Smarther2CommException, Smarther2LoginException {
        if (!isConnected()) {
            connect();
        }
    }

    public List<Smarther2Device> getDeviceList() throws Smarther2CommException, Smarther2LoginException {
        connectIfNotConnected();
        return connection.fetchDeviceList();
    }

    public boolean isConnected() {
        return connection.isConnected();
    }

    private void startConnectionCheck() {
        if (connectionCheckTask == null || connectionCheckTask.isCancelled()) {
            logger.debug("Start periodic connection check");
            Runnable runnable = () -> {
                logger.debug("Check Smarther2 connection");
                if (connection.isConnected()) {
                    logger.debug("Connection to Smarther2 open");
                } else {
                    try {
                        connect();
                    } catch (Smarther2LoginException e) {
                        logger.debug("Connection to Smarther2 down due to login error, reason: {}.", e.getMessage());
                    } catch (Smarther2CommException e) {
                        logger.debug("Connection to Smarther2 down, reason: {}.", e.getMessage());
                    } catch (RuntimeException e) {
                        logger.warn("Unknown error occurred during connection check, reason: {}.", e.getMessage(), e);
                    }
                }
            };
            connectionCheckTask = scheduler.scheduleWithFixedDelay(runnable, 0, 60, TimeUnit.SECONDS);
        } else {
            logger.debug("Connection check task already running");
        }
    }

    private void stopConnectionCheck() {
        if (connectionCheckTask != null) {
            logger.debug("Stop periodic connection check");
            connectionCheckTask.cancel(true);
            connectionCheckTask = null;
        }
    }

    public Smarther2DeviceStatus fetchDeviceStatus(String homeId, String roomId) throws Smarther2CommException {
        return connection.fetchDeviceStatus(homeId, roomId);
    }
}
