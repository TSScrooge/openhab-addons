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
public class ScheduleLimit {

    @SerializedName("nb_zones")
    @Expose
    private Integer nbZones;
    @SerializedName("nb_timeslots")
    @Expose
    private Integer nbTimeslots;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getNbZones() {
        return nbZones;
    }

    public void setNbZones(Integer nbZones) {
        this.nbZones = nbZones;
    }

    public Integer getNbTimeslots() {
        return nbTimeslots;
    }

    public void setNbTimeslots(Integer nbTimeslots) {
        this.nbTimeslots = nbTimeslots;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
