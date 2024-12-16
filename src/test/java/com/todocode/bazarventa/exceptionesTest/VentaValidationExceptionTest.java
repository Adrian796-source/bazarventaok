package com.todocode.bazarventa.exceptionesTest;

import com.todocode.bazarventa.exceptiones.VentaValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VentaValidationExceptionTest {


    @Test
    void testVentaValidationException() {
        // Given
        String message = "Datos de validación incorrectos";

        // When
        VentaValidationException exception = new VentaValidationException(message);

        // Then
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testVentaValidationExceptionWithCause() {
        // Given
        String message = "Datos de validación incorrectos";
        Throwable cause = new Throwable("Causa");

        // When
        VentaValidationException exception = new VentaValidationException(message, cause);

        // Then
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }


}
