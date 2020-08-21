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

package com.jalasoft.compress.model.entity;

import java.util.List;

public class CompressCommand {
    public String command;
    public String format;
    public String archiveName;
    public List<String> fileNames;
    public String sourcePath;
    public String targetPath;
    public String sevenZPath;

    /**
     * Setters
     */
    public CompressCommand setCommand(String command) {
        this.command = command;
        return this;
    }

    public CompressCommand setFormat(String format) {
        this.format = format;
        return this;
    }

    public CompressCommand setArchiveName(String archiveName) {
        this.archiveName = archiveName.replaceAll("\\.[^.]*$", "");
        return this;
    }

    public CompressCommand setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
        return this;
    }

    public CompressCommand setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
        return this;
    }

    public CompressCommand setTargetPath(String targetPath) {
        this.targetPath = targetPath;
        return this;
    }

    public CompressCommand setSevenZPath(String sevenZPath) {
        this.sevenZPath = sevenZPath;
        return this;
    }

    /**
     * Getters
     */

    public String getCommand() {
        return command;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public String getFormat() {
        return format;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public String getSevenZPath() {
        return sevenZPath;
    }
}
