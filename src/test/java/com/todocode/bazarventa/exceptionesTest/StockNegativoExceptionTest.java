package com.todocode.bazarventa.exceptionesTest;

import com.todocode.bazarventa.exceptiones.StockNegativoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockNegativoExceptionTest {

    @Test
    void testStockNegativoException() {
        // Given
        Long codigoProducto = 1L;

        // When
        StockNegativoException exception = new StockNegativoException(codigoProducto);

        // Then
        assertEquals("Stock negativo para el producto con código: 1", exception.getMessage());
        assertEquals(codigoProducto, exception.getCodigoProducto());
    }

}
