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

package com.jalasoft.compress.controller.entity;

import com.jalasoft.compress.common.exception.InvalidDataException;
import com.jalasoft.compress.common.validation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class CompressParam {
    private String format;
    private List<MultipartFile> files;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public void validate() throws InvalidDataException {
        List<IValidationStrategy> validationStrategies = new ArrayList<>();
        validationStrategies.add(new NotNullOrEmptyValidation("format", this.format));
        validationStrategies.add(new MultipartFileValidation(this.files));
        validationStrategies.add(new FormatValidation(this.format));

        ValidationContext context = new ValidationContext(validationStrategies);
        context.validation();
    }
}
