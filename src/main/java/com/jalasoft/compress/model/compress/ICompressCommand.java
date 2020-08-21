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
import com.jalasoft.compress.model.entity.CompressCommand;

public interface ICompressCommand {
    public String build(CompressCommand compressCommand) throws InvalidDataException;
}
