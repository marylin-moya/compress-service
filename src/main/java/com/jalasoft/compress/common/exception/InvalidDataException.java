/*
 *
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 *  with Jalasoft.
 *
 */

package com.jalasoft.compress.common.exception;

public class InvalidDataException extends Exception {
    private static final String GENERAL_MESSAGE = "Invalid Parameter.";
    private static final String FIELD_MESSAGE = "Invalid value = %s, in field = %s";

    public InvalidDataException() {
        super(GENERAL_MESSAGE);
    }

    public InvalidDataException(String currentMessage) {
        super(currentMessage);
    }

    public InvalidDataException(Throwable ex) {
        super(GENERAL_MESSAGE, ex);
    }

    public InvalidDataException(String currentMessage, Throwable ex) {
        super(currentMessage, ex);
    }

    public InvalidDataException(String field, String value) {
        super(String.format(FIELD_MESSAGE, value, field));
    }
}
