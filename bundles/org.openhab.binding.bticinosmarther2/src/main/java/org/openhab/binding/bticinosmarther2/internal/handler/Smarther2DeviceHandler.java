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

import static org.openhab.binding.bticinosmarther2.internal.Smarther2BindingConstants.CHANNEL_HUMIDITY;
import static org.openhab.binding.bticinosmarther2.internal.Smarther2BindingConstants.CHANNEL_TEMPERATURE;

import java.math.BigDecimal;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.openhab.binding.bticinosmarther2.internal.api.json.Smarther2DeviceStatus;
import org.openhab.binding.bticinosmarther2.internal.config.Smarther2DeviceConfig;
import org.openhab.binding.bticinosmarther2.internal.exceptions.Smarther2CommException;
import org.openhab.core.library.types.QuantityType;
import org.openhab.core.library.unit.SIUnits;
import org.openhab.core.library.unit.Units;
import org.openhab.core.thing.*;
import org.openhab.core.thing.binding.BaseThingHandler;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.types.Command;
import org.openhab.core.types.RefreshType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link Smarther2DeviceHandler} is the handler for Smarther2 API and connects it
 * to the webservice.
 *
 * @author Giacomo Agostini - Initial contribution
 */
public class Smarther2DeviceHandler extends BaseThingHandler {
    private final Logger logger = LoggerFactory.getLogger(Smarther2DeviceHandler.class);

    private ScheduledFuture<?> refreshTask;
    private Smarther2DeviceConfig config;
    private Smarther2AccountHandler smarther2AccountHandler;
    private boolean loginCredentialError;

    public Smarther2DeviceHandler(Thing thing) {
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

        config = getConfigAs(Smarther2DeviceConfig.class);
        logger.debug("A.C. device config: {}", config);

        initializeBridge(bridge.getHandler(), bridge.getStatus());
    }

    private void initializeBridge(ThingHandler thingHandler, ThingStatus bridgeStatus) {
        logger.debug("initializeBridge {} for thing {}", bridgeStatus, getThing().getUID());

        if (thingHandler != null && bridgeStatus != null) {
            smarther2AccountHandler = (Smarther2AccountHandler) thingHandler;

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
        smarther2AccountHandler = null;
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
        if (smarther2AccountHandler.isConnected()) {
            logger.debug("Update device '{}' channels", getThing().getThingTypeUID());
            Smarther2DeviceStatus deviceStatus = null;
            try {
                deviceStatus = smarther2AccountHandler.fetchDeviceStatus(config.homeId, config.roomId);
                updateChannels(deviceStatus);
            } catch (Smarther2CommException e) {
                logger.error("Cannot communicate");
            } catch (Exception e) {
                logger.error("Generic Error on Update");
            }
        } else {
            logger.debug("Connection to Smarther2 is not open, skipping periodic update");
        }
    }

    private synchronized void updateChannels(Smarther2DeviceStatus smarther2DeviceStatus) {
        updateState(CHANNEL_HUMIDITY,
                new QuantityType<>(new BigDecimal(smarther2DeviceStatus.getHumidity()), Units.PERCENT));
        updateState(CHANNEL_TEMPERATURE, new QuantityType<>(smarther2DeviceStatus.getTemperature(), SIUnits.CELSIUS));
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        logger.debug("Received command '{}' to channel {}", command, channelUID);

        if (command instanceof RefreshType) {
            logger.debug("Refresh command not supported");
            return;
        }
    }
}
