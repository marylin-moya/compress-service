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

package com.jalasoft.compress.controller;

import com.jalasoft.compress.controller.response.ErrorResponse;
import com.jalasoft.compress.controller.response.OKResponse;
import com.jalasoft.compress.controller.response.Response;
import com.jalasoft.compress.exception.FileException;
import com.jalasoft.compress.exception.InvalidDataException;
import com.jalasoft.compress.exception.ProcessException;
import com.jalasoft.compress.model.CompressFacade;
import com.jalasoft.compress.model.result.Result;
import com.jalasoft.compress.utils.FileHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CompressController {

    @Value("${file.sourcePath}")
    private String sourcePath;

    private static final Logger LOGGER = LogManager.getLogger();

    String thirdPartyFolderPath = "D:\\OOP\\code\\compress-service\\third-party\\7zip\\";
    String sourceFolderPath = "D:\\OOP\\code\\compress-service\\src\\main\\resources\\sourcePath\\";
    String targetFolderPath = "D:\\OOP\\code\\compress-service\\src\\main\\resources\\targetPath\\";

    @PostMapping("/compress")
    public ResponseEntity<Response> execute(CompressParam compressParam) {
        LOGGER.info("/compress rest method starting...");
        Result result;
        try {
            compressParam.validate();

            LOGGER.info("Getting Compress Params...");
            String format = compressParam.getFormat();
            List<MultipartFile> multipartFiles = compressParam.getFiles();

            LOGGER.info("Uploading {} files to source folder", multipartFiles.size());
            for (int i = 0; i < multipartFiles.size(); i++) {
                MultipartFile file = multipartFiles.get(i);
                try {
                    LOGGER.info("Uploading file: {}", file.getOriginalFilename());
                    FileHelper.saveUploadFile(sourcePath, file);
                } catch (FileException e) {
                    return ResponseEntity.badRequest().body(
                            new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
                }
            }

            List<String> files = multipartFiles.stream().map(file -> file.getOriginalFilename()).collect(Collectors.toList());
            System.out.println(files.toString());
            LOGGER.info("Compressing {} files to {} format", files.size(), format);
            result = CompressFacade.compress(files, format);

        } catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        } catch (ProcessException e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }

        return ResponseEntity.ok().body(
                new OKResponse<Integer>(HttpServletResponse.SC_OK, result.getResultConsole())
        );
    }
}
