package com.tsupryk.api;

/**
 * The Class RestResponse.
 * <p/>
 * Copyright (C) 2012 copyright.com
 * <p/>
 * Date: 08.10.13
 *
 * @author Vitaliy_Tsupryk
 */
public class RestResponse {

    private String result;

    private Object resposeBody;

    public RestResponse(String result, Object resposeBody) {
        this.result = result;
        this.resposeBody = resposeBody;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getResposeBody() {
        return resposeBody;
    }

    public void setResposeBody(Object resposeBody) {
        this.resposeBody = resposeBody;
    }
}
