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
package org.openhab.binding.bticinosmarther2.internal.api.json.homestatus;

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
public class Room {

    @SerializedName("anticipating")
    @Expose
    private Boolean anticipating;
    @SerializedName("cooling_setpoint_end_time")
    @Expose
    private Object coolingSetpointEndTime;
    @SerializedName("cooling_setpoint_mode")
    @Expose
    private String coolingSetpointMode;
    @SerializedName("cooling_setpoint_start_time")
    @Expose
    private Integer coolingSetpointStartTime;
    @SerializedName("cooling_setpoint_temperature")
    @Expose
    private Object coolingSetpointTemperature;
    @SerializedName("heating_power_request")
    @Expose
    private Integer heatingPowerRequest;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("open_window")
    @Expose
    private Boolean openWindow;
    @SerializedName("reachable")
    @Expose
    private Boolean reachable;
    @SerializedName("therm_measured_temperature")
    @Expose
    private Double thermMeasuredTemperature;

    public Boolean getAnticipating() {
        return anticipating;
    }

    public void setAnticipating(Boolean anticipating) {
        this.anticipating = anticipating;
    }

    public Object getCoolingSetpointEndTime() {
        return coolingSetpointEndTime;
    }

    public void setCoolingSetpointEndTime(Object coolingSetpointEndTime) {
        this.coolingSetpointEndTime = coolingSetpointEndTime;
    }

    public String getCoolingSetpointMode() {
        return coolingSetpointMode;
    }

    public void setCoolingSetpointMode(String coolingSetpointMode) {
        this.coolingSetpointMode = coolingSetpointMode;
    }

    public Integer getCoolingSetpointStartTime() {
        return coolingSetpointStartTime;
    }

    public void setCoolingSetpointStartTime(Integer coolingSetpointStartTime) {
        this.coolingSetpointStartTime = coolingSetpointStartTime;
    }

    public Object getCoolingSetpointTemperature() {
        return coolingSetpointTemperature;
    }

    public void setCoolingSetpointTemperature(Object coolingSetpointTemperature) {
        this.coolingSetpointTemperature = coolingSetpointTemperature;
    }

    public Integer getHeatingPowerRequest() {
        return heatingPowerRequest;
    }

    public void setHeatingPowerRequest(Integer heatingPowerRequest) {
        this.heatingPowerRequest = heatingPowerRequest;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOpenWindow() {
        return openWindow;
    }

    public void setOpenWindow(Boolean openWindow) {
        this.openWindow = openWindow;
    }

    public Boolean getReachable() {
        return reachable;
    }

    public void setReachable(Boolean reachable) {
        this.reachable = reachable;
    }

    public Double getThermMeasuredTemperature() {
        return thermMeasuredTemperature;
    }

    public void setThermMeasuredTemperature(Double thermMeasuredTemperature) {
        this.thermMeasuredTemperature = thermMeasuredTemperature;
    }
}
