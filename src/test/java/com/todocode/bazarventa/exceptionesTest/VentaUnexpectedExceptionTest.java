package com.todocode.bazarventa.exceptionesTest;

import com.todocode.bazarventa.exceptiones.VentaUnexpectedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VentaUnexpectedExceptionTest {


    @Test
    void testVentaUnexpectedException() {
        // Given
        String message = "Error inesperado";
        Exception cause = new Exception("Causa");

        // When
        VentaUnexpectedException exception = new VentaUnexpectedException(message, cause);

        // Then
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

}
