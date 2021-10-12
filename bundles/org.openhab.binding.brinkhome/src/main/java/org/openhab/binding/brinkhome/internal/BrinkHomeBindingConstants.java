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

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.core.thing.ThingTypeUID;

/**
 * The {@link BrinkHomeBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Giacomo Agostini - Initial contribution
 */
@NonNullByDefault
public class BrinkHomeBindingConstants {

    private static final String BINDING_ID = "brinkhome";

    public static final ThingTypeUID THING_TYPE_BRINKHOME_ACCOUNT = new ThingTypeUID(BINDING_ID, "brinkhomeaccount");
    public static final ThingTypeUID THING_TYPE_VENTILATION_DEVICE = new ThingTypeUID(BINDING_ID, "ventilationdevice");

    // List of all Channel ids
    public static final String CHANNEL_VENTILATION_SPEED = "ventilationSpeed";
    public static final String CHANNEL_OPERATION_MODE = "operationMode";
    public static final String CHANNEL_REMAINING_TIME = "remainingTime";
    public static final String CHANNEL_BYPASS_VALVE_STATUS = "bypassValveStatus";
    public static final String CHANNEL_FILTER_STATUS = "filterStatus";

    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPE_UIDS = Collections.unmodifiableSet(
            Stream.of(THING_TYPE_BRINKHOME_ACCOUNT, THING_TYPE_VENTILATION_DEVICE).collect(Collectors.toSet()));

    public static final Set<ThingTypeUID> DISCOVERABLE_THING_TYPE_UIDS = Collections
            .unmodifiableSet(Stream.of(THING_TYPE_VENTILATION_DEVICE).collect(Collectors.toSet()));
}
