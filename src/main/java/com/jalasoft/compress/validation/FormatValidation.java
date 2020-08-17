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

package com.jalasoft.compress.validation;

import com.jalasoft.compress.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public class FormatValidation implements IValidationStrategy {
    private String format;
    private List<String> FORMAT_LIST;

    public FormatValidation(String format) throws InvalidDataException {
        this.format = format;
        this.FORMAT_LIST = new ArrayList<String>() {
            {
                add("zip");
                add("7z");
                add("tar");
            }
        };
        System.out.println(this.FORMAT_LIST.toString());
    }

    @Override
    public void validate() throws InvalidDataException {
        if (!FORMAT_LIST.contains(this.format)) {
            throw new InvalidDataException("Unsupported format");
        }
    }
}
