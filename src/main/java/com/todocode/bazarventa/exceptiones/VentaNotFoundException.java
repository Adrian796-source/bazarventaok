package com.todocode.bazarventa.exceptiones;

public class VentaNotFoundException extends RuntimeException {
    public VentaNotFoundException(String message) {
        super(message);
    }

}
