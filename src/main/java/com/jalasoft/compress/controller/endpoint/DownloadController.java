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

package com.jalasoft.compress.controller.endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static com.jalasoft.compress.common.utils.Constants.BASE_PATH;

/***
 * Download Controller class to download a file
 */

@RestController
@RequestMapping(BASE_PATH)
public class DownloadController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Value("${file.targetPath}")
    private String targetPath;

    @GetMapping("/download/{fileName:.+}")
    public void getCompressedFiles(HttpServletResponse response,
                                   @PathVariable("fileName") String fileName) {
        String fullPathName = String.format("%s%s", targetPath, fileName);
        String commonContentType = "application/octet-stream";
        try {
            File file = new File(fullPathName);
            if (file.exists()) {
                response.setContentType(commonContentType);    // Download the file directly
                InputStream is = new BufferedInputStream(new FileInputStream(fullPathName));
                FileCopyUtils.copy(is, response.getOutputStream());
            }
        } catch (IOException ex) {
            LOGGER.info("It is not possible download compressed File..." + ex.getMessage());
        }
    }
}
