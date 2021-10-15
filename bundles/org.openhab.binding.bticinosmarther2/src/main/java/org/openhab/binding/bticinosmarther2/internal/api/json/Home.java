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
public class Home {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("altitude")
    @Expose
    private Integer altitude;
    @SerializedName("coordinates")
    @Expose
    private List<Double> coordinates = null;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("nb_users")
    @Expose
    private Integer nbUsers;
    @SerializedName("place_improved")
    @Expose
    private Boolean placeImproved;
    @SerializedName("trust_location")
    @Expose
    private Boolean trustLocation;
    @SerializedName("therm_absence_notification")
    @Expose
    private Boolean thermAbsenceNotification;
    @SerializedName("therm_absence_autoaway")
    @Expose
    private Boolean thermAbsenceAutoaway;
    @SerializedName("temperature_control_mode")
    @Expose
    private String temperatureControlMode;
    @SerializedName("therm_mode")
    @Expose
    private String thermMode;
    @SerializedName("therm_setpoint_default_duration")
    @Expose
    private Integer thermSetpointDefaultDuration;
    @SerializedName("anticipation")
    @Expose
    private Boolean anticipation;
    @SerializedName("therm_heating_priority")
    @Expose
    private String thermHeatingPriority;
    @SerializedName("cooling_mode")
    @Expose
    private String coolingMode;
    @SerializedName("therm_boost_default_duration")
    @Expose
    private Integer thermBoostDefaultDuration;
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;
    @SerializedName("modules")
    @Expose
    private List<Module> modules = null;
    @SerializedName("therm_schedules")
    @Expose
    private List<ThermSchedule> thermSchedules = null;
    @SerializedName("schedules")
    @Expose
    private List<Schedule> schedules = null;

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

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getNbUsers() {
        return nbUsers;
    }

    public void setNbUsers(Integer nbUsers) {
        this.nbUsers = nbUsers;
    }

    public Boolean getPlaceImproved() {
        return placeImproved;
    }

    public void setPlaceImproved(Boolean placeImproved) {
        this.placeImproved = placeImproved;
    }

    public Boolean getTrustLocation() {
        return trustLocation;
    }

    public void setTrustLocation(Boolean trustLocation) {
        this.trustLocation = trustLocation;
    }

    public Boolean getThermAbsenceNotification() {
        return thermAbsenceNotification;
    }

    public void setThermAbsenceNotification(Boolean thermAbsenceNotification) {
        this.thermAbsenceNotification = thermAbsenceNotification;
    }

    public Boolean getThermAbsenceAutoaway() {
        return thermAbsenceAutoaway;
    }

    public void setThermAbsenceAutoaway(Boolean thermAbsenceAutoaway) {
        this.thermAbsenceAutoaway = thermAbsenceAutoaway;
    }

    public String getTemperatureControlMode() {
        return temperatureControlMode;
    }

    public void setTemperatureControlMode(String temperatureControlMode) {
        this.temperatureControlMode = temperatureControlMode;
    }

    public String getThermMode() {
        return thermMode;
    }

    public void setThermMode(String thermMode) {
        this.thermMode = thermMode;
    }

    public Integer getThermSetpointDefaultDuration() {
        return thermSetpointDefaultDuration;
    }

    public void setThermSetpointDefaultDuration(Integer thermSetpointDefaultDuration) {
        this.thermSetpointDefaultDuration = thermSetpointDefaultDuration;
    }

    public Boolean getAnticipation() {
        return anticipation;
    }

    public void setAnticipation(Boolean anticipation) {
        this.anticipation = anticipation;
    }

    public String getThermHeatingPriority() {
        return thermHeatingPriority;
    }

    public void setThermHeatingPriority(String thermHeatingPriority) {
        this.thermHeatingPriority = thermHeatingPriority;
    }

    public String getCoolingMode() {
        return coolingMode;
    }

    public void setCoolingMode(String coolingMode) {
        this.coolingMode = coolingMode;
    }

    public Integer getThermBoostDefaultDuration() {
        return thermBoostDefaultDuration;
    }

    public void setThermBoostDefaultDuration(Integer thermBoostDefaultDuration) {
        this.thermBoostDefaultDuration = thermBoostDefaultDuration;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<ThermSchedule> getThermSchedules() {
        return thermSchedules;
    }

    public void setThermSchedules(List<ThermSchedule> thermSchedules) {
        this.thermSchedules = thermSchedules;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
