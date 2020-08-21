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

import com.jalasoft.compress.common.exception.FileException;
import com.jalasoft.compress.common.exception.InvalidDataException;
import com.jalasoft.compress.common.exception.ProcessException;
import com.jalasoft.compress.common.utils.FileHelper;
import com.jalasoft.compress.controller.entity.CompressParam;
import com.jalasoft.compress.controller.response.ErrorResponse;
import com.jalasoft.compress.controller.response.OKResponse;
import com.jalasoft.compress.controller.response.Response;
import com.jalasoft.compress.model.compress.CompressFacade;
import com.jalasoft.compress.model.result.Result;
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

import static com.jalasoft.compress.common.utils.Constants.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH)
public class CompressController {

    @Value("${file.sourcePath}")
    private String sourcePath;

    @Value("${file.targetPath}")
    private String targetPath;

    @Value("${server.address}")
    private String serverAddress;

    @Value("${server.port}")
    private String serverPort;

    private static final Logger LOGGER = LogManager.getLogger();

    @PostMapping("/compress")
    public ResponseEntity<Response> execute(CompressParam compressParam) {
        LOGGER.info("/compress rest method starting...");
        Result result;
        try {
            LOGGER.info("Validating Compress Params...");
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
            LOGGER.info("Compressing {} files to {} format", files.size(), format);
            result = CompressFacade.compress(files, format, sourcePath, targetPath);

        } catch (InvalidDataException e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        } catch (ProcessException e) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()));
        }

        String downloadString = String.format("http://%s:%s%s/download/%s.%s", serverAddress, serverPort, BASE_PATH,
                compressParam.getFiles().get(0).getOriginalFilename().replaceAll("\\.[^.]*$", ""),
                compressParam.getFormat());
        return ResponseEntity.ok().body(
                new OKResponse<Integer>(HttpServletResponse.SC_OK, result.getResultConsole(), downloadString)
        );
    }
}
