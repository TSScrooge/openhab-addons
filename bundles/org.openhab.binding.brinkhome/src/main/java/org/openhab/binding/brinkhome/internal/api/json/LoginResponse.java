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
public class LoginResponse {

    @SerializedName("isPasswordReset")
    @Expose
    private Boolean isPasswordReset;
    @SerializedName("isExpert")
    @Expose
    private Boolean isExpert;
    @SerializedName("serverWebApiVersion")
    @Expose
    private Integer serverWebApiVersion;

    public Boolean getIsPasswordReset() {
        return isPasswordReset;
    }

    public void setIsPasswordReset(Boolean isPasswordReset) {
        this.isPasswordReset = isPasswordReset;
    }

    public Boolean getIsExpert() {
        return isExpert;
    }

    public void setIsExpert(Boolean isExpert) {
        this.isExpert = isExpert;
    }

    public Integer getServerWebApiVersion() {
        return serverWebApiVersion;
    }

    public void setServerWebApiVersion(Integer serverWebApiVersion) {
        this.serverWebApiVersion = serverWebApiVersion;
    }
}
