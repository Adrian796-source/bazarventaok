package com.todocode.bazarventa.exceptiones;


public class VentaDataAccessException extends Exception {

    private final String detail;

    public VentaDataAccessException(String message, String detailedMessage, Throwable cause) {
        super(message + ": " + detailedMessage, cause);
        this.detail = detailedMessage;
    }

    public String getDetail() {
        return detail;
    }
}

