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
package org.openhab.binding.brinkhome.internal.exceptions;

/**
 * Exception to encapsulate any issues communicating with MELCloud.
 *
 * @author Pauli Anttila - Initial Contribution
 */
public class BrinkHomeCommException extends Exception {
    private static final long serialVersionUID = 1L;

    public BrinkHomeCommException(Throwable cause) {
        super("Error occurred when communicating with BrinkHome", cause);
    }

    public BrinkHomeCommException(String message) {
        super(message);
    }

    public BrinkHomeCommException(String message, Throwable cause) {
        super(message, cause);
    }
}
