package com.todocode.bazarventa.exceptiones;

public class StockNegativoException extends Exception {

    private final Long codigoProducto;

    // Constructor que acepta solo el código del producto
    public StockNegativoException(Long codigoProducto) {
        super("Stock negativo para el producto con código: " + codigoProducto);
        this.codigoProducto = codigoProducto;
    }

    public Long getCodigoProducto() {
        return codigoProducto;
    }
}