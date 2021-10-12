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
public class ListItem {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("displayText")
    @Expose
    private String displayText;
    @SerializedName("imageName")
    @Expose
    private String imageName;
    @SerializedName("isSelectable")
    @Expose
    private Boolean isSelectable;
    @SerializedName("highlightIfSelected")
    @Expose
    private Boolean highlightIfSelected;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Boolean getIsSelectable() {
        return isSelectable;
    }

    public void setIsSelectable(Boolean isSelectable) {
        this.isSelectable = isSelectable;
    }

    public Boolean getHighlightIfSelected() {
        return highlightIfSelected;
    }

    public void setHighlightIfSelected(Boolean highlightIfSelected) {
        this.highlightIfSelected = highlightIfSelected;
    }
}
