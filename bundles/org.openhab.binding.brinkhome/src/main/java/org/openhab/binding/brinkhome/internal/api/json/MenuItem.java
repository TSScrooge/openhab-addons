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

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Config class for BrinkHome account parameters.
 *
 * @author Giacomo Agostini - Initial Contribution
 *
 */
public class MenuItem {

    @SerializedName("guiId")
    @Expose
    private Integer guiId;
    @SerializedName("pages")
    @Expose
    private List<Page> pages = null;

    public Integer getGuiId() {
        return guiId;
    }

    public void setGuiId(Integer guiId) {
        this.guiId = guiId;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
