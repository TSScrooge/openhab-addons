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
package org.openhab.binding.bticinosmarther2.internal;

import static org.openhab.binding.bticinosmarther2.internal.Smarther2BindingConstants.*;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.bticinosmarther2.internal.handler.Smarther2AccountHandler;
import org.openhab.binding.bticinosmarther2.internal.handler.Smarther2DeviceHandler;
import org.openhab.core.thing.Bridge;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.binding.BaseThingHandlerFactory;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.annotations.Component;

/**
 * The {@link Smarther2HandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Giacomo Agostini - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.brinkhome", service = ThingHandlerFactory.class)
public class Smarther2HandlerFactory extends BaseThingHandlerFactory {

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPE_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (THING_TYPE_SMARTHER2_ACCOUNT.equals(thingTypeUID)) {
            Smarther2AccountHandler handler = new Smarther2AccountHandler((Bridge) thing);
            return handler;
        } else if (THING_TYPE_SMARTHER2_DEVICE.equals(thingTypeUID)) {
            Smarther2DeviceHandler handler = new Smarther2DeviceHandler(thing);
            return handler;
        }

        return null;
    }
}
