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

package com.jalasoft.compress.model.result;

public class Result {
    private String resultConsole;

    public Result(String resultConsole) {
        this.resultConsole = resultConsole;
    }

    public String getResultConsole() {
        return resultConsole;
    }

    public void setResultConsole(String resultConsole) {
        this.resultConsole = resultConsole;
    }

}
