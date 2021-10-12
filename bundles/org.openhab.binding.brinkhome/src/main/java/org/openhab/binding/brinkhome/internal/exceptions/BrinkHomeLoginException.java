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
 * Exception to encapsulate any login issues with MELCloud.
 *
 * @author Pauli Anttila - Initial Contribution
 */
public class BrinkHomeLoginException extends Exception {
    private static final long serialVersionUID = 1L;

    public BrinkHomeLoginException(Throwable cause) {
        super("Error occurred during login to BrinkHome", cause);
    }

    public BrinkHomeLoginException(String message) {
        super(message);
    }

    public BrinkHomeLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
