package com.todocode.bazarventa.exceptionesTest;

import com.todocode.bazarventa.exceptiones.ProductoNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductoNotFoundExceptionTest {


    @Test
    void testProductoNotFoundException() {
        // Given
        String message = "Producto no encontrado";

        // When
        ProductoNotFoundException exception = new ProductoNotFoundException(message);

        // Then
        assertEquals(message, exception.getMessage());
    }

}

