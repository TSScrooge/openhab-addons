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
public class ThermSchedule {

    @SerializedName("timetable")
    @Expose
    private List<Timetable> timetable = null;
    @SerializedName("zones")
    @Expose
    private List<Zone> zones = null;
    @SerializedName("default")
    @Expose
    private Boolean _default;
    @SerializedName("away_temp")
    @Expose
    private Integer awayTemp;
    @SerializedName("hg_temp")
    @Expose
    private Integer hgTemp;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("selected")
    @Expose
    private Boolean selected;
    @SerializedName("type")
    @Expose
    private String type;

    public List<Timetable> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<Timetable> timetable) {
        this.timetable = timetable;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public Boolean getDefault() {
        return _default;
    }

    public void setDefault(Boolean _default) {
        this._default = _default;
    }

    public Integer getAwayTemp() {
        return awayTemp;
    }

    public void setAwayTemp(Integer awayTemp) {
        this.awayTemp = awayTemp;
    }

    public Integer getHgTemp() {
        return hgTemp;
    }

    public void setHgTemp(Integer hgTemp) {
        this.hgTemp = hgTemp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
