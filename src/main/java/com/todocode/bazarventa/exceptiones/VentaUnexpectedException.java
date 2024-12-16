package com.todocode.bazarventa.exceptiones;

public class VentaUnexpectedException extends RuntimeException {
    public VentaUnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }
}
