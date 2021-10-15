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

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.core.thing.ThingTypeUID;

/**
 * The {@link Smarther2BindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Giacomo Agostini - Initial contribution
 */
@NonNullByDefault
public class Smarther2BindingConstants {

    private static final String BINDING_ID = "bticinosmarther2";

    public static final ThingTypeUID THING_TYPE_SMARTHER2_ACCOUNT = new ThingTypeUID(BINDING_ID, "smarther2account");
    public static final ThingTypeUID THING_TYPE_SMARTHER2_DEVICE = new ThingTypeUID(BINDING_ID, "smarther2device");

    // List of all Channel ids
    public static final String CHANNEL_TEMPERATURE = "temperature";
    public static final String CHANNEL_HUMIDITY = "humidity";

    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPE_UIDS = Collections.unmodifiableSet(
            Stream.of(THING_TYPE_SMARTHER2_ACCOUNT, THING_TYPE_SMARTHER2_DEVICE).collect(Collectors.toSet()));

    public static final Set<ThingTypeUID> DISCOVERABLE_THING_TYPE_UIDS = Collections
            .unmodifiableSet(Stream.of(THING_TYPE_SMARTHER2_DEVICE).collect(Collectors.toSet()));
}
