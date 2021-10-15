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

import static org.openhab.binding.brinkhome.internal.BrinkHomeBindingConstants.*;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.openhab.binding.brinkhome.internal.api.json.ParameterValuesResponse;
import org.openhab.binding.brinkhome.internal.api.json.Value;
import org.openhab.binding.brinkhome.internal.config.VentilationDeviceConfig;
import org.openhab.binding.brinkhome.internal.exceptions.BrinkHomeCommException;
import org.openhab.core.library.types.StringType;
import org.openhab.core.thing.*;
import org.openhab.core.thing.binding.BaseThingHandler;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.types.Command;
import org.openhab.core.types.RefreshType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link VentilationDeviceHandler} is the handler for BrinkHome API and connects it
 * to the webservice.
 *
 * @author Giacomo Agostini - Initial contribution
 */
public class VentilationDeviceHandler extends BaseThingHandler {
    private final Logger logger = LoggerFactory.getLogger(VentilationDeviceHandler.class);

    private ScheduledFuture<?> refreshTask;
    private VentilationDeviceConfig config;
    private BrinkHomeAccountHandler brinkHomeAccountHandler;
    private boolean loginCredentialError;

    public VentilationDeviceHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void initialize() {
        logger.debug("Initializing {} handler.", getThing().getThingTypeUID());

        Bridge bridge = getBridge();
        if (bridge == null) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR, "Bridge Not set");
            return;
        }

        config = getConfigAs(VentilationDeviceConfig.class);
        logger.debug("A.C. device config: {}", config);

        initializeBridge(bridge.getHandler(), bridge.getStatus());
    }

    private void initializeBridge(ThingHandler thingHandler, ThingStatus bridgeStatus) {
        logger.debug("initializeBridge {} for thing {}", bridgeStatus, getThing().getUID());

        if (thingHandler != null && bridgeStatus != null) {
            brinkHomeAccountHandler = (BrinkHomeAccountHandler) thingHandler;

            if (bridgeStatus == ThingStatus.ONLINE) {
                updateStatus(ThingStatus.ONLINE);
                startAutomaticRefresh();
            } else {
                updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.BRIDGE_OFFLINE);
            }
        } else {
            updateStatus(ThingStatus.OFFLINE);
        }
    }

    @Override
    public void dispose() {
        logger.debug("Running dispose()");
        if (refreshTask != null) {
            refreshTask.cancel(true);
            refreshTask = null;
        }
        brinkHomeAccountHandler = null;
    }

    @Override
    public void bridgeStatusChanged(ThingStatusInfo bridgeStatusInfo) {
        logger.debug("bridgeStatusChanged {} for thing {}", bridgeStatusInfo, getThing().getUID());
        Bridge bridge = getBridge();
        if (bridge != null) {
            initializeBridge(bridge.getHandler(), bridgeStatusInfo.getStatus());
        }
    }

    private void startAutomaticRefresh() {
        if (refreshTask == null || refreshTask.isCancelled()) {
            refreshTask = scheduler.scheduleWithFixedDelay(this::getDeviceDataAndUpdateChannels, 1,
                    config.pollingInterval, TimeUnit.SECONDS);
        }
    }

    private void getDeviceDataAndUpdateChannels() {
        if (brinkHomeAccountHandler.isConnected()) {
            logger.debug("Update device '{}' channels", getThing().getThingTypeUID());
            ParameterValuesResponse devicesStatus = null;
            try {
                devicesStatus = brinkHomeAccountHandler.fetchDeviceStatus(config.systemId, config.gatewayId);
                for (Value deviceStatus : devicesStatus.getValues())
                    updateChannels(deviceStatus);
            } catch (BrinkHomeCommException e) {
                logger.error("Cannot communicate");
            }
        } else {
            logger.debug("Connection to BrinkHome is not open, skipping periodic update");
        }
    }

    private synchronized void updateChannels(Value parameterValue) {
        switch (parameterValue.getValueId()) {
            case 242083:
                updateState(CHANNEL_VENTILATION_SPEED, new StringType(parameterValue.getValue().toString()));
                break;
            case 242084:
                updateState(CHANNEL_OPERATION_MODE, new StringType(parameterValue.getValue().toString()));
                break;
            case 242116:
                updateState(CHANNEL_FILTER_STATUS, new StringType(parameterValue.getValue().toString()));
                break;
            default:
                break;
        }
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        logger.debug("Received command '{}' to channel {}", command, channelUID);

        if (command instanceof RefreshType) {
            logger.debug("Refresh command not supported");
            return;
        }

        if (brinkHomeAccountHandler == null) {
            logger.warn("No connection to MELCloud available, ignoring command");
            return;
        }
        switch (channelUID.getId()) {
            case CHANNEL_VENTILATION_SPEED:
                try {
                    brinkHomeAccountHandler.sendVentilationCommand(config.systemId, config.gatewayId,
                            Integer.parseInt(command.toString()));
                } catch (BrinkHomeCommException e) {
                    logger.warn("Command '{}' to channel '{}' failed due to login error, reason {}. ", command,
                            channelUID, e.getMessage(), e);
                }
                break;
            case CHANNEL_OPERATION_MODE:
                try {
                    brinkHomeAccountHandler.sendOperationModeCommand(config.systemId, config.gatewayId,
                            Integer.parseInt(command.toString()));
                } catch (BrinkHomeCommException e) {
                    logger.warn("Command '{}' to channel '{}' failed due to login error, reason {}. ", command,
                            channelUID, e.getMessage(), e);
                }
                break;
            default:
                logger.debug("Read-only or unknown channel {}, skipping update", channelUID);
        }
    }
}
