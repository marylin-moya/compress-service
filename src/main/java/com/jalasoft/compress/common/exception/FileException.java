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

public class FileException extends Exception {
    private static final String MESSAGE = "Error.";

    public FileException() {
        super(MESSAGE);
    }

    public FileException(Throwable ex) {
        super(MESSAGE, ex);
    }

    public FileException(String currantMessage, Throwable ex) {
        super(currantMessage, ex);
    }

    public FileException(String currentMessage) {
        super(currentMessage);
    }

}
