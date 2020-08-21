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

public class Constants {
    public static final String OS_NAME_PROPERTY = "os.name";
    public final static String CONFIGURATION_FILE = "configuration.properties";
    public final static String COMPRESS_FORMATS = "compress.formats";
    public final static String COMPRESS_WINDOWS_TOOL = "compress.windows.tool";
    public final static String COMPRESS_LINUX_TOOL = "compress.linux.tool";
    public final static String COMPRESS_COMMAND = "a";
    public final static String COMPRESS_WINDOWS_COMMAND = "7za.exe";
    public final static String COMPRESS_LINUX_COMMAND = "7za";
    public final static String BASE_PATH = "/api/v1";

    public static enum OS {
        WINDOWS("windows"), LINUX("linux");

        public String name;

        OS(String name) {
            this.name = name;
        }

        public String toName() {
            return name;
        }
    }
}
