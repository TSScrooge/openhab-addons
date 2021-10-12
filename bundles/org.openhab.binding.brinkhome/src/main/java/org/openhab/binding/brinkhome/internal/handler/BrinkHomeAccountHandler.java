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
package org.openhab.binding.brinkhome.internal.handler;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.openhab.binding.brinkhome.internal.api.BrinkHomeConnection;
import org.openhab.binding.brinkhome.internal.api.json.ParameterValuesResponse;
import org.openhab.binding.brinkhome.internal.api.json.SystemListResponse;
import org.openhab.binding.brinkhome.internal.config.AccountConfig;
import org.openhab.binding.brinkhome.internal.discovery.BrinkHomeDiscoveryService;
import org.openhab.binding.brinkhome.internal.exceptions.BrinkHomeCommException;
import org.openhab.binding.brinkhome.internal.exceptions.BrinkHomeLoginException;
import org.openhab.core.thing.*;
import org.openhab.core.thing.binding.BaseBridgeHandler;
import org.openhab.core.thing.binding.ThingHandlerService;
import org.openhab.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link BrinkHomeAccountHandler} is the handler for BrinkHome API and connects it
 * to the webservice.
 *
 * @author Giacomo Agostini - Initial contribution
 */
public class BrinkHomeAccountHandler extends BaseBridgeHandler {
    private final Logger logger = LoggerFactory.getLogger(BrinkHomeAccountHandler.class);

    private BrinkHomeConnection connection;
    private ScheduledFuture<?> connectionCheckTask;
    private AccountConfig config;
    private boolean loginCredentialError;
    private ScheduledFuture<?> refreshTask;

    public BrinkHomeAccountHandler(Bridge bridge) {
        super(bridge);
    }

    @Override
    public Collection<Class<? extends ThingHandlerService>> getServices() {
        return Collections.singleton(BrinkHomeDiscoveryService.class);
    }

    @Override
    public void initialize() {
        logger.debug("Initializing BrinkHome account handler.");
        config = getConfigAs(AccountConfig.class);
        connection = new BrinkHomeConnection();
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
            refreshTask = scheduler.scheduleWithFixedDelay(this::loginNoException, 300, 300, TimeUnit.SECONDS);
        }
    }

    public ThingUID getID() {
        return getThing().getUID();
    }

    private void connect() throws BrinkHomeCommException, BrinkHomeLoginException {
        if (loginCredentialError) {
            throw new BrinkHomeLoginException("Connection to BrinkHome can't be opened because of wrong credentials");
        }
        logger.debug("Initializing connection to BrinkHome");
        updateStatus(ThingStatus.OFFLINE);
        try {
            connection.login(config.username, config.password);
            updateStatus(ThingStatus.ONLINE);
        } catch (BrinkHomeCommException e) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, e.getMessage());
            throw e;
        }
        startAutomaticRefresh();
    }

    private void loginNoException() {
        try {
            connection.login(config.username, config.password);
        } catch (Exception e) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, e.getMessage());
            logger.warn("Connection to BrinkHome down due to login error, reason: {}.", e.getMessage());
        }
    }

    private synchronized void connectIfNotConnected() throws BrinkHomeCommException, BrinkHomeLoginException {
        if (!isConnected()) {
            connect();
        }
    }

    public List<SystemListResponse> getDeviceList() throws BrinkHomeCommException, BrinkHomeLoginException {
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
                logger.debug("Check BrinkHome connection");
                if (connection.isConnected()) {
                    logger.debug("Connection to BrinkHome open");
                } else {
                    try {
                        connect();
                    } catch (BrinkHomeLoginException e) {
                        logger.debug("Connection to BrinkHome down due to login error, reason: {}.", e.getMessage());
                    } catch (BrinkHomeCommException e) {
                        logger.debug("Connection to BrinkHome down, reason: {}.", e.getMessage());
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

    public void sendVentilationCommand(Integer systemId, Integer gatewayId, int speed) throws BrinkHomeCommException {
        connection.sendVentilationCommand(systemId, gatewayId, speed);
    }

    public void sendOperationModeCommand(Integer systemId, Integer gatewayId, int operationMode)
            throws BrinkHomeCommException {
        connection.sendOperationModeCommand(systemId, gatewayId, operationMode);
    }

    public ParameterValuesResponse fetchDeviceStatus(Integer systemId, Integer gatewayId)
            throws BrinkHomeCommException {
        return connection.fetchDeviceStatus(systemId, gatewayId);
    }
}
