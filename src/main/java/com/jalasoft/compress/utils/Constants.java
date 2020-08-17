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

package com.jalasoft.compress.utils;

public class Constants {
    public static final String OS_NAME_PROPERTY = "os.name";

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
