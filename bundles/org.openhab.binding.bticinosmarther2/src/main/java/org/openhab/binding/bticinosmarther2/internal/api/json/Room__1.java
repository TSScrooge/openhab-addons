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
package org.openhab.binding.bticinosmarther2.internal.api.json;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Config class for Smarther2 account parameters.
 *
 * @author Giacomo Agostini - Initial Contribution
 *
 */
@Generated("jsonschema2pojo")
public class Room__1 {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("therm_setpoint_temperature")
    @Expose
    private Integer thermSetpointTemperature;
    @SerializedName("cooling_setpoint_mode")
    @Expose
    private String coolingSetpointMode;
    @SerializedName("cooling_setpoint_temperature")
    @Expose
    private Integer coolingSetpointTemperature;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getThermSetpointTemperature() {
        return thermSetpointTemperature;
    }

    public void setThermSetpointTemperature(Integer thermSetpointTemperature) {
        this.thermSetpointTemperature = thermSetpointTemperature;
    }

    public String getCoolingSetpointMode() {
        return coolingSetpointMode;
    }

    public void setCoolingSetpointMode(String coolingSetpointMode) {
        this.coolingSetpointMode = coolingSetpointMode;
    }

    public Integer getCoolingSetpointTemperature() {
        return coolingSetpointTemperature;
    }

    public void setCoolingSetpointTemperature(Integer coolingSetpointTemperature) {
        this.coolingSetpointTemperature = coolingSetpointTemperature;
    }
}
