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

/**
 * Config class for Smarther2 account parameters.
 *
 * @author Giacomo Agostini - Initial Contribution
 *
 */
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
public class Module {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("setup_date")
    @Expose
    private Integer setupDate;
    @SerializedName("room_id")
    @Expose
    private String roomId;
    @SerializedName("reachable")
    @Expose
    private Boolean reachable;
    @SerializedName("hk_device_id")
    @Expose
    private String hkDeviceId;
    @SerializedName("schedule_limits")
    @Expose
    private List<ScheduleLimit> scheduleLimits = null;
    @SerializedName("max_modules_nb")
    @Expose
    private Integer maxModulesNb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(Integer setupDate) {
        this.setupDate = setupDate;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Boolean getReachable() {
        return reachable;
    }

    public void setReachable(Boolean reachable) {
        this.reachable = reachable;
    }

    public String getHkDeviceId() {
        return hkDeviceId;
    }

    public void setHkDeviceId(String hkDeviceId) {
        this.hkDeviceId = hkDeviceId;
    }

    public List<ScheduleLimit> getScheduleLimits() {
        return scheduleLimits;
    }

    public void setScheduleLimits(List<ScheduleLimit> scheduleLimits) {
        this.scheduleLimits = scheduleLimits;
    }

    public Integer getMaxModulesNb() {
        return maxModulesNb;
    }

    public void setMaxModulesNb(Integer maxModulesNb) {
        this.maxModulesNb = maxModulesNb;
    }
}
