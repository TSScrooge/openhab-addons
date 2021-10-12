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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Config class for BrinkHome account parameters.
 *
 * @author Giacomo Agostini - Initial Contribution
 *
 */
public class SystemListResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("gatewayId")
    @Expose
    private Integer gatewayId;
    @SerializedName("isForeignSystem")
    @Expose
    private Boolean isForeignSystem;
    @SerializedName("accessLevel")
    @Expose
    private Integer accessLevel;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Integer gatewayId) {
        this.gatewayId = gatewayId;
    }

    public Boolean getIsForeignSystem() {
        return isForeignSystem;
    }

    public void setIsForeignSystem(Boolean isForeignSystem) {
        this.isForeignSystem = isForeignSystem;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
