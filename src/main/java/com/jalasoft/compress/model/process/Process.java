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

import com.jalasoft.compress.exception.ProcessException;
import com.jalasoft.compress.model.result.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Process {

    private static final Logger LOGGER = LogManager.getLogger();

    public abstract ProcessBuilder getProcessBuilder(String command);

    public Result Execute(String command) throws ProcessException {
        try {
            LOGGER.info("Starting the command execution...");
            ProcessBuilder builder = getProcessBuilder(command);
            builder.redirectErrorStream(true);

            LOGGER.info("Execute command: {}", command);
            java.lang.Process process = builder.start();
            process.waitFor();

            if (process.exitValue() != 0) {
                throw new ProcessException("Error executing command");
            }

            LOGGER.info("Reading output...");
            InputStreamReader streamReader = new InputStreamReader(process.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            StringBuilder result = new StringBuilder();
            while (reader.ready()) {
                result.append((char) reader.read());
            }

            return new Result(result.toString());
        } catch (IOException | InterruptedException e) {
            throw new ProcessException(e.getMessage());
        }
    }
}
