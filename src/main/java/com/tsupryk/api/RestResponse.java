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

    private Object responseBody;

    public RestResponse(String result, Object responseBody) {
        this.result = result;
        this.responseBody = responseBody;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }
}
