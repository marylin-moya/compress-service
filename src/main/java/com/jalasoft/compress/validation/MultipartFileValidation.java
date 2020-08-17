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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MultipartFileValidation implements IValidationStrategy {
    private List<MultipartFile> files;

    public MultipartFileValidation(List<MultipartFile> files) {
        this.files = files;
    }

    @Override
    public void validate() throws InvalidDataException {
        for (MultipartFile multipartFile : files) {
            if (multipartFile == null || multipartFile.isEmpty() || multipartFile.getOriginalFilename().contains("..")) {
                throw new InvalidDataException("Invalid File");
            }
        }
    }
}
