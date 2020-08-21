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
import com.jalasoft.compress.common.utils.ConfigurationProperty;
import com.jalasoft.compress.common.utils.Constants;
import com.jalasoft.compress.model.entity.CompressCommand;

public class LinuxCompressCommand implements ICompressCommand {

    @Override
    public String build(CompressCommand compressCommand) throws InvalidDataException {
        String spaceBlank = " ";
        return new StringBuilder()
                //.append(compressCommand.sevenZPath)
                .append(ConfigurationProperty.getLinuxCompressToolPath())
                .append(Constants.COMPRESS_LINUX_COMMAND)
                .append(spaceBlank)
                .append(compressCommand.command)
                .append(spaceBlank)
                //.append(this.options.stream().reduce("-", String::concat))
                .append(String.format("-t%s", compressCommand.format))
                .append(spaceBlank)
                .append(compressCommand.targetPath)
                .append(String.format("\"%s.%s\"", compressCommand.archiveName, compressCommand.format))
                .append(spaceBlank)
                .append(compressCommand.sourcePath)
                .append(compressCommand.fileNames.stream()
                        .map(filename -> String.format("\"%s\"%s", filename, spaceBlank))
                        .reduce("", String::concat))
                .toString().trim();
        // return "D:\\OOP\\code\\compress-service\\third-party\\7zip\\7za.exe a -ttar D:\\OOP\\code\\compress-service\\src\\main\\resources\\targetPath\\\"Page_Object_Model___Trello.tar\"  D:\\OOP\\code\\compress-service\\src\\main\\resources\\sourcePath\\\"Page_Object_Model___Trello.pdf\"";
    }
}
