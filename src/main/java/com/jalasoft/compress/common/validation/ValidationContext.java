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

import java.util.List;

public class ValidationContext {
    private List<IValidationStrategy> validationStrategies;

    public ValidationContext(List<IValidationStrategy> validationStrategies) {
        this.validationStrategies = validationStrategies;
    }

    public void validation() throws InvalidDataException {
        for (IValidationStrategy strategy : this.validationStrategies) {
            strategy.validate();
        }
    }
}
