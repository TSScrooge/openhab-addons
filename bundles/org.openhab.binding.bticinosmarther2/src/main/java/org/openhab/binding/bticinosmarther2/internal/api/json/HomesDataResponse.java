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
public class HomesDataResponse {

    @SerializedName("body")
    @Expose
    private Body body;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("time_exec")
    @Expose
    private Double timeExec;
    @SerializedName("time_server")
    @Expose
    private Integer timeServer;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTimeExec() {
        return timeExec;
    }

    public void setTimeExec(Double timeExec) {
        this.timeExec = timeExec;
    }

    public Integer getTimeServer() {
        return timeServer;
    }

    public void setTimeServer(Integer timeServer) {
        this.timeServer = timeServer;
    }
}
