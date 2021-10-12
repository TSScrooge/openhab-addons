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
public class ParameterDescriptor {

    @SerializedName("uiId")
    @Expose
    private String uiId;
    @SerializedName("valueId")
    @Expose
    private Integer valueId;
    @SerializedName("sortId")
    @Expose
    private Integer sortId;
    @SerializedName("subBundleId")
    @Expose
    private Integer subBundleId;
    @SerializedName("parameterId")
    @Expose
    private Integer parameterId;
    @SerializedName("readWrite")
    @Expose
    private Integer readWrite;
    @SerializedName("noDataPoint")
    @Expose
    private Boolean noDataPoint;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("controlType")
    @Expose
    private Integer controlType;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("valueState")
    @Expose
    private Integer valueState;
    @SerializedName("hasDependentParameter")
    @Expose
    private Boolean hasDependentParameter;
    @SerializedName("listItems")
    @Expose
    private List<ListItem> listItems = null;
    @SerializedName("minValue")
    @Expose
    private Integer minValue;
    @SerializedName("maxValue")
    @Expose
    private Integer maxValue;
    @SerializedName("stepWidth")
    @Expose
    private Integer stepWidth;
    @SerializedName("decimals")
    @Expose
    private Integer decimals;

    public String getUiId() {
        return uiId;
    }

    public void setUiId(String uiId) {
        this.uiId = uiId;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Integer getSubBundleId() {
        return subBundleId;
    }

    public void setSubBundleId(Integer subBundleId) {
        this.subBundleId = subBundleId;
    }

    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public Integer getReadWrite() {
        return readWrite;
    }

    public void setReadWrite(Integer readWrite) {
        this.readWrite = readWrite;
    }

    public Boolean getNoDataPoint() {
        return noDataPoint;
    }

    public void setNoDataPoint(Boolean noDataPoint) {
        this.noDataPoint = noDataPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getControlType() {
        return controlType;
    }

    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getValueState() {
        return valueState;
    }

    public void setValueState(Integer valueState) {
        this.valueState = valueState;
    }

    public Boolean getHasDependentParameter() {
        return hasDependentParameter;
    }

    public void setHasDependentParameter(Boolean hasDependentParameter) {
        this.hasDependentParameter = hasDependentParameter;
    }

    public List<ListItem> getListItems() {
        return listItems;
    }

    public void setListItems(List<ListItem> listItems) {
        this.listItems = listItems;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getStepWidth() {
        return stepWidth;
    }

    public void setStepWidth(Integer stepWidth) {
        this.stepWidth = stepWidth;
    }

    public Integer getDecimals() {
        return decimals;
    }

    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }
}
