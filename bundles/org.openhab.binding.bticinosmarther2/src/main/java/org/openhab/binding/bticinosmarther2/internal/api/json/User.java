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
public class User {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("feel_like_algorithm")
    @Expose
    private Integer feelLikeAlgorithm;
    @SerializedName("unit_pressure")
    @Expose
    private Integer unitPressure;
    @SerializedName("unit_system")
    @Expose
    private Integer unitSystem;
    @SerializedName("unit_wind")
    @Expose
    private Integer unitWind;
    @SerializedName("all_linked")
    @Expose
    private Boolean allLinked;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("app_telemetry")
    @Expose
    private Boolean appTelemetry;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getFeelLikeAlgorithm() {
        return feelLikeAlgorithm;
    }

    public void setFeelLikeAlgorithm(Integer feelLikeAlgorithm) {
        this.feelLikeAlgorithm = feelLikeAlgorithm;
    }

    public Integer getUnitPressure() {
        return unitPressure;
    }

    public void setUnitPressure(Integer unitPressure) {
        this.unitPressure = unitPressure;
    }

    public Integer getUnitSystem() {
        return unitSystem;
    }

    public void setUnitSystem(Integer unitSystem) {
        this.unitSystem = unitSystem;
    }

    public Integer getUnitWind() {
        return unitWind;
    }

    public void setUnitWind(Integer unitWind) {
        this.unitWind = unitWind;
    }

    public Boolean getAllLinked() {
        return allLinked;
    }

    public void setAllLinked(Boolean allLinked) {
        this.allLinked = allLinked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAppTelemetry() {
        return appTelemetry;
    }

    public void setAppTelemetry(Boolean appTelemetry) {
        this.appTelemetry = appTelemetry;
    }
}
