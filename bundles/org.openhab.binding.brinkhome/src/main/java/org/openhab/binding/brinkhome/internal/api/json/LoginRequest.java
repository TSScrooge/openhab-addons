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
public class LoginRequest {

    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("RememberMe")
    @Expose
    private Boolean rememberMe;
    @SerializedName("RemoteIpAddress")
    @Expose
    private Object remoteIpAddress;
    @SerializedName("UserAgent")
    @Expose
    private Object userAgent;
    @SerializedName("AppVersion")
    @Expose
    private String appVersion;
    @SerializedName("WebsiteVersion")
    @Expose
    private Integer websiteVersion;
    @SerializedName("WebApiVersion")
    @Expose
    private Integer webApiVersion;
    @SerializedName("UserRole")
    @Expose
    private Integer userRole;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public Object getRemoteIpAddress() {
        return remoteIpAddress;
    }

    public void setRemoteIpAddress(Object remoteIpAddress) {
        this.remoteIpAddress = remoteIpAddress;
    }

    public Object getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(Object userAgent) {
        this.userAgent = userAgent;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getWebsiteVersion() {
        return websiteVersion;
    }

    public void setWebsiteVersion(Integer websiteVersion) {
        this.websiteVersion = websiteVersion;
    }

    public Integer getWebApiVersion() {
        return webApiVersion;
    }

    public void setWebApiVersion(Integer webApiVersion) {
        this.webApiVersion = webApiVersion;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }
}
