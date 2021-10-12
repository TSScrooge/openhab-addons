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

/**
 * Config class for BrinkHome account parameters.
 *
 * @author Giacomo Agostini - Initial Contribution
 *
 */
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Config class for BrinkHome account parameters.
 *
 * @author Giacomo Agostini - Initial Contribution
 *
 */
public class WriteParameterValueRequest {

    @SerializedName("GatewayId")
    @Expose
    private Integer gatewayId;
    @SerializedName("SystemId")
    @Expose
    private Integer systemId;
    @SerializedName("GuiId")
    @Expose
    private Integer guiId;
    @SerializedName("WriteParameterValues")
    @Expose
    private List<WriteParameterValue> writeParameterValues = null;
    @SerializedName("DependendReadValuesAfterWrite")
    @Expose
    private List<Integer> dependendReadValuesAfterWrite = null;
    @SerializedName("SendInOneBundle")
    @Expose
    private Boolean sendInOneBundle;

    public Integer getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Integer gatewayId) {
        this.gatewayId = gatewayId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Integer getGuiId() {
        return guiId;
    }

    public void setGuiId(Integer guiId) {
        this.guiId = guiId;
    }

    public List<WriteParameterValue> getWriteParameterValues() {
        return writeParameterValues;
    }

    public void setWriteParameterValues(List<WriteParameterValue> writeParameterValues) {
        this.writeParameterValues = writeParameterValues;
    }

    public List<Integer> getDependendReadValuesAfterWrite() {
        return dependendReadValuesAfterWrite;
    }

    public void setDependendReadValuesAfterWrite(List<Integer> dependendReadValuesAfterWrite) {
        this.dependendReadValuesAfterWrite = dependendReadValuesAfterWrite;
    }

    public Boolean getSendInOneBundle() {
        return sendInOneBundle;
    }

    public void setSendInOneBundle(Boolean sendInOneBundle) {
        this.sendInOneBundle = sendInOneBundle;
    }
}
