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
package org.openhab.binding.bticinosmarther2.internal.exceptions;

/**
 * Exception to encapsulate any issues communicating with MELCloud.
 *
 * @author Pauli Anttila - Initial Contribution
 */
public class Smarther2CommException extends Exception {
    private static final long serialVersionUID = 1L;

    public Smarther2CommException(Throwable cause) {
        super("Error occurred when communicating with BrinkHome", cause);
    }

    public Smarther2CommException(String message) {
        super(message);
    }

    public Smarther2CommException(String message, Throwable cause) {
        super(message, cause);
    }
}
