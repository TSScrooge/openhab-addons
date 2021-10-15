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

import java.util.List;

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

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("module_ids")
    @Expose
    private List<String> moduleIds = null;
    @SerializedName("modules")
    @Expose
    private List<String> modules = null;
    @SerializedName("therm_relay")
    @Expose
    private String thermRelay;
    @SerializedName("true_temperature_available")
    @Expose
    private Boolean trueTemperatureAvailable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getModuleIds() {
        return moduleIds;
    }

    public void setModuleIds(List<String> moduleIds) {
        this.moduleIds = moduleIds;
    }

    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }

    public String getThermRelay() {
        return thermRelay;
    }

    public void setThermRelay(String thermRelay) {
        this.thermRelay = thermRelay;
    }

    public Boolean getTrueTemperatureAvailable() {
        return trueTemperatureAvailable;
    }

    public void setTrueTemperatureAvailable(Boolean trueTemperatureAvailable) {
        this.trueTemperatureAvailable = trueTemperatureAvailable;
    }
}
