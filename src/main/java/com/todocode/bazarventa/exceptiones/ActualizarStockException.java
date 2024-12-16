package com.todocode.bazarventa.exceptiones;

public class ActualizarStockException extends Exception {

    public ActualizarStockException(String message) {
      super(message);
    }

    public ActualizarStockException(String message, Throwable cause) {
      super(message, cause);
    }
  }