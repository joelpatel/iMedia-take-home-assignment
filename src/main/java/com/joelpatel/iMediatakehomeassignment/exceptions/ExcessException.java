package com.joelpatel.iMediatakehomeassignment.exceptions;

import org.springframework.http.HttpStatus;

public class ExcessException extends RuntimeException {
    long excess;
    HttpStatus statusCode;

    public ExcessException(long excess, HttpStatus statusCode) {
        this.excess = excess;
        this.statusCode = statusCode;
    }

    public long getExcess() {
        return excess;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
