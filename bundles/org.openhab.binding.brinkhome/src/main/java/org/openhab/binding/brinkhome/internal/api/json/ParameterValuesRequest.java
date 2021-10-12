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
package org.openhab.binding.brinkhome.internal.api.json;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Config class for BrinkHome account parameters.
 *
 * @author Giacomo Agostini - Initial Contribution
 *
 */
public class ParameterValuesRequest {

    @SerializedName("SystemId")
    @Expose
    private Integer systemId;
    @SerializedName("GatewayId")
    @Expose
    private Integer gatewayId;
    @SerializedName("GuiId")
    @Expose
    private Integer guiId;
    @SerializedName("SessionId")
    @Expose
    private Object sessionId;
    @SerializedName("GuiIdChanged")
    @Expose
    private Boolean guiIdChanged;
    @SerializedName("ValueIdList")
    @Expose
    private List<Integer> valueIdList = null;
    @SerializedName("LastAccess")
    @Expose
    private String lastAccess;
    @SerializedName("SystemBusSamplingRateSec")
    @Expose
    private Integer systemBusSamplingRateSec;
    @SerializedName("IsSubBundle")
    @Expose
    private Boolean isSubBundle;

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Integer gatewayId) {
        this.gatewayId = gatewayId;
    }

    public Integer getGuiId() {
        return guiId;
    }

    public void setGuiId(Integer guiId) {
        this.guiId = guiId;
    }

    public Object getSessionId() {
        return sessionId;
    }

    public void setSessionId(Object sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getGuiIdChanged() {
        return guiIdChanged;
    }

    public void setGuiIdChanged(Boolean guiIdChanged) {
        this.guiIdChanged = guiIdChanged;
    }

    public List<Integer> getValueIdList() {
        return valueIdList;
    }

    public void setValueIdList(List<Integer> valueIdList) {
        this.valueIdList = valueIdList;
    }

    public String getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(String lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Integer getSystemBusSamplingRateSec() {
        return systemBusSamplingRateSec;
    }

    public void setSystemBusSamplingRateSec(Integer systemBusSamplingRateSec) {
        this.systemBusSamplingRateSec = systemBusSamplingRateSec;
    }

    public Boolean getIsSubBundle() {
        return isSubBundle;
    }

    public void setIsSubBundle(Boolean isSubBundle) {
        this.isSubBundle = isSubBundle;
    }
}
