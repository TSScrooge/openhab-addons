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
package org.openhab.binding.bticinosmarther2.internal.discovery;

import static org.openhab.binding.bticinosmarther2.internal.Smarther2BindingConstants.THING_TYPE_SMARTHER2_DEVICE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.bticinosmarther2.internal.Smarther2BindingConstants;
import org.openhab.binding.bticinosmarther2.internal.api.json.Smarther2Device;
import org.openhab.binding.bticinosmarther2.internal.exceptions.Smarther2CommException;
import org.openhab.binding.bticinosmarther2.internal.exceptions.Smarther2LoginException;
import org.openhab.binding.bticinosmarther2.internal.handler.Smarther2AccountHandler;
import org.openhab.core.config.discovery.AbstractDiscoveryService;
import org.openhab.core.config.discovery.DiscoveryResultBuilder;
import org.openhab.core.config.discovery.DiscoveryService;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingUID;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.thing.binding.ThingHandlerService;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link Smarther2DiscoveryService} creates things based on the configured location.
 *
 * @author Luca Calcaterra - Initial Contribution
 * @author Pauli Anttila - Refactoring
 * @author Wietse van Buitenen - Check device type, added heatpump device
 */
public class Smarther2DiscoveryService extends AbstractDiscoveryService
        implements DiscoveryService, ThingHandlerService {

    private final Logger logger = LoggerFactory.getLogger(Smarther2DiscoveryService.class);

    private static final String PROPERTY_DEVICE_ID = "deviceID";
    private static final int DISCOVER_TIMEOUT_SECONDS = 10;

    private Smarther2AccountHandler smarther2AccountHandler;
    private ScheduledFuture<?> scanTask;

    /**
     * Creates a MelCloudDiscoveryService with enabled autostart.
     */
    public Smarther2DiscoveryService() {
        super(Smarther2BindingConstants.DISCOVERABLE_THING_TYPE_UIDS, DISCOVER_TIMEOUT_SECONDS, true);
    }

    @Override
    protected void activate(Map<String, Object> configProperties) {
        super.activate(configProperties);
    }

    @Override
    public void deactivate() {
        super.deactivate();
    }

    @Override
    @Modified
    protected void modified(Map<String, Object> configProperties) {
        super.modified(configProperties);
    }

    @Override
    protected void startBackgroundDiscovery() {
        discoverDevices();
    }

    @Override
    protected void startScan() {
        if (this.scanTask != null) {
            scanTask.cancel(true);
        }
        this.scanTask = scheduler.schedule(() -> discoverDevices(), 0, TimeUnit.SECONDS);
    }

    @Override
    protected void stopScan() {
        super.stopScan();

        if (this.scanTask != null) {
            this.scanTask.cancel(true);
            this.scanTask = null;
        }
    }

    private void discoverDevices() {
        logger.debug("Discover devices");
        if (smarther2AccountHandler != null) {
            try {
                List<Smarther2Device> deviceList = smarther2AccountHandler.getDeviceList();

                if (deviceList == null) {
                    logger.debug("No devices found");
                } else {
                    ThingUID bridgeUID = smarther2AccountHandler.getThing().getUID();

                    deviceList.forEach(device -> {
                        ThingUID deviceThing = new ThingUID(THING_TYPE_SMARTHER2_DEVICE,
                                smarther2AccountHandler.getThing().getUID(), device.getRoomId());

                        Map<String, Object> deviceProperties = new HashMap<>();
                        deviceProperties.put(PROPERTY_DEVICE_ID, device.getRoomId());
                        deviceProperties.put(Thing.PROPERTY_SERIAL_NUMBER, device.getRoomId());
                        deviceProperties.put("deviceId", device.getDeviceId());
                        deviceProperties.put("roomId", device.getRoomId());
                        deviceProperties.put("homeId", device.getHomeId());

                        String label = createLabel(device);
                        logger.debug("Found device: {} : {}", label, deviceProperties);

                        thingDiscovered(DiscoveryResultBuilder.create(deviceThing).withLabel(label)
                                .withProperties(deviceProperties).withRepresentationProperty(PROPERTY_DEVICE_ID)
                                .withBridge(bridgeUID).build());
                    });
                }
            } catch (Smarther2LoginException e) {
                logger.debug("Error occurred during device list fetch, reason {}. ", e.getMessage(), e);
            } catch (Smarther2CommException e) {
                logger.debug("Login error occurred during device list fetch, reason {}. ", e.getMessage(), e);
            }
        }
    }

    @Override
    public void setThingHandler(@Nullable ThingHandler handler) {
        if (handler instanceof Smarther2AccountHandler) {
            smarther2AccountHandler = (Smarther2AccountHandler) handler;
        }
    }

    private String createLabel(Smarther2Device device) {
        StringBuilder sb = new StringBuilder();
        sb.append("Smarther2 - ");
        sb.append(device.getRoomName());
        return sb.toString();
    }

    @Override
    public @Nullable ThingHandler getThingHandler() {
        return smarther2AccountHandler;
    }
}
