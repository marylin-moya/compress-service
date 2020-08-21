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

package com.jalasoft.compress.common.utils;

import com.jalasoft.compress.common.exception.InvalidDataException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertyHandler {
    private static PropertyHandler propertyHandler;
    private static Properties properties;
    private final static String SEPARATOR = ",";

    private PropertyHandler() throws InvalidDataException {
        try {
            this.properties = new Properties();
            this.properties.load(getClass().getClassLoader().getResourceAsStream(Constants.CONFIGURATION_FILE));
        } catch (IOException ex) {
            throw new InvalidDataException(ex);
        }
    }

    public static PropertyHandler getInstance() throws InvalidDataException {
        if (propertyHandler == null) {
            propertyHandler = new PropertyHandler();
        }
        return propertyHandler;
    }

    public String getValueAsString(String key) {
        return this.properties.getProperty(key);
    }

    public List<String> getValueAsList(String key) {
        String value = this.getValueAsString(key);
        return new ArrayList<>(Arrays.asList(
                value.split(SEPARATOR)
        ));
    }
}
