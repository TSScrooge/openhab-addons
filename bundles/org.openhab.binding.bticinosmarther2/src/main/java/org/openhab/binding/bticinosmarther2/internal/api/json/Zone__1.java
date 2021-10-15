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
public class Zone__1 {

    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("rooms_temp")
    @Expose
    private List<RoomsTemp__1> roomsTemp = null;
    @SerializedName("rooms")
    @Expose
    private List<Room__1> rooms = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("modules")
    @Expose
    private List<Object> modules = null;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<RoomsTemp__1> getRoomsTemp() {
        return roomsTemp;
    }

    public void setRoomsTemp(List<RoomsTemp__1> roomsTemp) {
        this.roomsTemp = roomsTemp;
    }

    public List<Room__1> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room__1> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getModules() {
        return modules;
    }

    public void setModules(List<Object> modules) {
        this.modules = modules;
    }
}
