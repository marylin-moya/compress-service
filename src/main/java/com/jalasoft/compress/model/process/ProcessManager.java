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

package com.jalasoft.compress.model.process;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.jalasoft.compress.utils.Constants.OS;
import static com.jalasoft.compress.utils.Constants.OS_NAME_PROPERTY;

public class ProcessManager {

    private static final Logger LOGGER = LogManager.getLogger();

    public Process getProcess() {
        String os = System.getProperty(OS_NAME_PROPERTY);
        if (os.toLowerCase().contains(OS.WINDOWS.toName())) {
            LOGGER.info("Return '{}' Process object.", OS.WINDOWS.toName());
            return new WindowsProcess();
        } else if (os.toLowerCase().contains(OS.LINUX.toName())) {
            LOGGER.info("Return '{}' Process object.", OS.LINUX.toName());
            return new LinuxProcess();
        }
        return null;
    }
}
