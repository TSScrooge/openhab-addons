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
package org.openhab.binding.brinkhome.internal;

import static org.openhab.binding.brinkhome.internal.BrinkHomeBindingConstants.*;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.brinkhome.internal.handler.BrinkHomeAccountHandler;
import org.openhab.binding.brinkhome.internal.handler.VentilationDeviceHandler;
import org.openhab.core.thing.Bridge;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.binding.BaseThingHandlerFactory;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.annotations.Component;

/**
 * The {@link BrinkHomeHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Giacomo Agostini - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.brinkhome", service = ThingHandlerFactory.class)
public class BrinkHomeHandlerFactory extends BaseThingHandlerFactory {

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPE_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (THING_TYPE_BRINKHOME_ACCOUNT.equals(thingTypeUID)) {
            BrinkHomeAccountHandler handler = new BrinkHomeAccountHandler((Bridge) thing);
            return handler;
        } else if (THING_TYPE_VENTILATION_DEVICE.equals(thingTypeUID)) {
            VentilationDeviceHandler handler = new VentilationDeviceHandler(thing);
            return handler;
        }

        return null;
    }
}
