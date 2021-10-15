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
package org.openhab.binding.bticinosmarther2.internal.config;

/**
 * Config class for a A.C. device.
 *
 * @author Pauli Anttila - Initial Contribution
 *
 */
public class Smarther2DeviceConfig {

    public String deviceId;
    public String roomId;
    public String homeId;
    public Integer pollingInterval;

    @Override
    public String toString() {
        return "[deviceID=" + deviceId + ", buildingID=" + roomId + ", homeId=" + homeId + ", pollingInterval="
                + pollingInterval + "]";
    }
}
