package com.paweu.inzappbackend.models.exception;

public class ResponseExceptionModel extends Throwable {
    private final String msg;
    private final int statusCode;

    public ResponseExceptionModel(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    public int getStatusCode(){
        return this.statusCode;
    }
}
