package com.tsupryk.api;

/**
 * ServiceRuntimeException.
 * User: vital
 * Date: 9/12/13
 */
public class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException(Exception e) {
        super(e);
    }
}
