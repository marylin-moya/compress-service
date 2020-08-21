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

import com.jalasoft.compress.common.exception.FileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * FileHelper class
 */
public class FileHelper {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Method to store the file in the fileSystem
     *
     * @param filePath
     * @param file
     * @throws FileException
     */
    static public void saveUploadFile(String filePath, MultipartFile file) throws FileException {
        try {
            if (!file.isEmpty()) {
                LOGGER.info("Storing {} file.", file.getOriginalFilename());
                byte[] bytes = file.getBytes();
                createFolder(filePath);
                Path path = Paths.get(String.format("%s%s", filePath, file.getOriginalFilename()));
                Files.write(path, bytes);
            } else {
                throw new FileException("File object is empty.");
            }
        } catch (IOException e) {
            throw new FileException("File Error", e);
        }
    }

    /**
     * Create the folder if it does not exist.
     *
     * @param folderPath
     */
    static public boolean createFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            LOGGER.info("{} folder does not exists. Creating the folder: ", folder);
            return folder.mkdirs();
        }
        return false;
    }
}
