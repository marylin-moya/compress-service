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

import java.util.List;

public class ConfigurationProperty {
    public static List<String> getFormats() throws InvalidDataException {
        return PropertyHandler.getInstance().getValueAsList(Constants.COMPRESS_FORMATS);
    }

    public static String getWindowsCompressToolPath() throws InvalidDataException {
        return PropertyHandler.getInstance().getValueAsString(Constants.COMPRESS_WINDOWS_TOOL);
    }

    public static String getLinuxCompressToolPath() throws InvalidDataException {
        return PropertyHandler.getInstance().getValueAsString(Constants.COMPRESS_LINUX_TOOL);
    }
}
