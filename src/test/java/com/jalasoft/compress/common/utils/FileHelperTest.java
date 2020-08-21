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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class FileHelperTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        File resourcesDirectory = new File("src/test/resources/FileHelperTest/target/mary");
        resourcesDirectory.delete();
    }

    @Test
    public void createFolder() {
        String testFolderPath = "src/test/resources/FileHelperTest/target/mary";
        boolean isCreated = FileHelper.createFolder(testFolderPath);
        assertTrue("Folder was not created", isCreated);
    }

    @Test
    public void saveUploadFile() throws IOException, FileException {
        String testFilePath = "src/test/resources/FileHelperTest/source/test.txt";
        Path path = Paths.get(testFilePath);
        byte[] content = Files.readAllBytes(path);
        MultipartFile multipartFile = new MockMultipartFile("test.txt", "test.txt", "text/plain", new FileInputStream(new File(testFilePath)));

        String testFolderPath = "src/test/resources/FileHelperTest/target/mary/";
        FileHelper.saveUploadFile(testFolderPath, multipartFile);
        File newfile = new File(testFolderPath + "test.txt");
        boolean fileExists = newfile.exists();
        newfile.deleteOnExit();

        assertTrue("Folder was not created", fileExists);
    }


}