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

package com.jalasoft.compress.model.compress;

import com.jalasoft.compress.common.exception.InvalidDataException;
import com.jalasoft.compress.common.exception.ProcessException;
import com.jalasoft.compress.common.utils.Constants;
import com.jalasoft.compress.model.entity.CompressCommand;
import com.jalasoft.compress.model.process.Process;
import com.jalasoft.compress.model.process.ProcessManager;
import com.jalasoft.compress.model.result.Result;

import java.util.List;

public class CompressFacade {

    public static Result compress(List<String> files, String format, String sourcePath,
                                  String targetPath) throws ProcessException, InvalidDataException {

        CompressCommand compressCommand = new CompressCommand()
                .setCommand(Constants.COMPRESS_COMMAND)
                .setFormat(format)
                .setTargetPath(targetPath)
                .setArchiveName(files.get(0))
                .setSourcePath(sourcePath)
                .setFileNames(files);

        ProcessManager processManager = new ProcessManager();
        Process process = processManager.getProcess();
        ICompressCommand iCompressCommand = new CompressManager().getCompressCommand();
        return process.Execute(iCompressCommand.build(compressCommand));
    }
}
