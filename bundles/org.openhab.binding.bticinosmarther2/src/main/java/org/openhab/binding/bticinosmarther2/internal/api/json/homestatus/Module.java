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
public class Module {

    @SerializedName("boiler_status")
    @Expose
    private Boolean boilerStatus;
    @SerializedName("boiler_valve_comfort_boost")
    @Expose
    private Boolean boilerValveComfortBoost;
    @SerializedName("cooler_status")
    @Expose
    private Boolean coolerStatus;
    @SerializedName("firmware_revision")
    @Expose
    private Integer firmwareRevision;
    @SerializedName("hardware_version")
    @Expose
    private Integer hardwareVersion;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("installation_type")
    @Expose
    private String installationType;
    @SerializedName("sequence_id")
    @Expose
    private Integer sequenceId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("wifi_strength")
    @Expose
    private Integer wifiStrength;

    public Boolean getBoilerStatus() {
        return boilerStatus;
    }

    public void setBoilerStatus(Boolean boilerStatus) {
        this.boilerStatus = boilerStatus;
    }

    public Boolean getBoilerValveComfortBoost() {
        return boilerValveComfortBoost;
    }

    public void setBoilerValveComfortBoost(Boolean boilerValveComfortBoost) {
        this.boilerValveComfortBoost = boilerValveComfortBoost;
    }

    public Boolean getCoolerStatus() {
        return coolerStatus;
    }

    public void setCoolerStatus(Boolean coolerStatus) {
        this.coolerStatus = coolerStatus;
    }

    public Integer getFirmwareRevision() {
        return firmwareRevision;
    }

    public void setFirmwareRevision(Integer firmwareRevision) {
        this.firmwareRevision = firmwareRevision;
    }

    public Integer getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(Integer hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstallationType() {
        return installationType;
    }

    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }

    public Integer getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWifiStrength() {
        return wifiStrength;
    }

    public void setWifiStrength(Integer wifiStrength) {
        this.wifiStrength = wifiStrength;
    }
}
