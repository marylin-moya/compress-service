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

package com.jalasoft.compress.common.validation;

import com.jalasoft.compress.common.exception.InvalidDataException;

public class NotNullOrEmptyValidation implements IValidationStrategy {
    private String value;
    private String field;

    public NotNullOrEmptyValidation(String field, String value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public void validate() throws InvalidDataException {
        if (this.value == null || this.value.trim().isEmpty()) {
            throw new InvalidDataException("Invalid data on field = " + this.field);
        }
    }
}
