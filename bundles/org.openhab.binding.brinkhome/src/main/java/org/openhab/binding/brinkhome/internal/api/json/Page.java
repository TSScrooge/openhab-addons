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
public class Page {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageName")
    @Expose
    private String imageName;
    @SerializedName("pageType")
    @Expose
    private Integer pageType;
    @SerializedName("parameterDescriptors")
    @Expose
    private List<ParameterDescriptor> parameterDescriptors = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getPageType() {
        return pageType;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }

    public List<ParameterDescriptor> getParameterDescriptors() {
        return parameterDescriptors;
    }

    public void setParameterDescriptors(List<ParameterDescriptor> parameterDescriptors) {
        this.parameterDescriptors = parameterDescriptors;
    }
}
