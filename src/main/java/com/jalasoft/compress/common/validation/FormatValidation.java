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
import com.jalasoft.compress.common.utils.ConfigurationProperty;

import java.util.List;

public class FormatValidation implements IValidationStrategy {
    private String format;
    private List<String> FORMAT_LIST = ConfigurationProperty.getFormats();

    public FormatValidation(String format) throws InvalidDataException {
        this.format = format;
    }

    @Override
    public void validate() throws InvalidDataException {
        if (!FORMAT_LIST.contains(this.format)) {
            throw new InvalidDataException("Unsupported format");
        }
    }
}
