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

package com.jalasoft.compress.model;

import com.jalasoft.compress.exception.ProcessException;
import com.jalasoft.compress.model.compress.CompressCommand;
import com.jalasoft.compress.model.compress.CompressManager;
import com.jalasoft.compress.model.compress.ICompressCommand;
import com.jalasoft.compress.model.process.Process;
import com.jalasoft.compress.model.process.ProcessManager;
import com.jalasoft.compress.model.result.Result;

import java.util.ArrayList;
import java.util.List;

public class CompressFacade {



    public static Result compress(List<String> files, String format) throws ProcessException {
        String thirdPartyFolderPath = "D:\\OOP\\code\\compress-service\\third-party\\7zip\\";
        String sourceFolderPath = "D:\\OOP\\code\\compress-service\\src\\main\\resources\\sourcePath\\";
        String targetFolderPath = "D:\\OOP\\code\\compress-service\\src\\main\\resources\\targetPath\\";

        CompressCommand compressCommand = new CompressCommand()
                .setSevenZPath(thirdPartyFolderPath)
                .setCommand("a")
                //.setOptions(options)
                .setFormat(format)
                .setTargetPath(targetFolderPath)
                .setArchiveName(files.get(0))
                .setSourcePath(sourceFolderPath)
                .setFileNames(files);

        ProcessManager processManager = new ProcessManager();
        Process process = processManager.getProcess();
        ICompressCommand iCompressCommand = new CompressManager().getCompressCommand();
        return process.Execute(iCompressCommand.build(compressCommand));
    }
}
