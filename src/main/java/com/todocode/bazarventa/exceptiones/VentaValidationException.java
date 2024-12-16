package com.todocode.bazarventa.exceptiones;


public class VentaValidationException extends Exception {
    public VentaValidationException(String message) {
        super(message);
    }

    public VentaValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}

