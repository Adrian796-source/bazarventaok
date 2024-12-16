package com.todocode.bazarventa.exceptionesTest;

import com.todocode.bazarventa.exceptiones.VentaDataAccessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VentaDataAccesExceptionTest {

    @Test
    void testVentaDataAccessException() {

        // Given
        String message = "Error de acceso a datos";
        String detail = "Detalles adicionales";
        Exception cause = new Exception("Causa");

        // When
        VentaDataAccessException exception = new VentaDataAccessException(message, detail, cause);

        // Then
        assertEquals(message + ": " + detail, exception.getMessage());
        assertEquals(detail, exception.getDetail());
        assertEquals(cause, exception.getCause());
    }

}
