package com.todocode.bazarventa.exceptionesTest;

import com.todocode.bazarventa.exceptiones.ActualizarStockException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


 class ActualizarStockExceptionTest {


    @Test
    void testActualizarStockExceptionMessage() {
        // Given
        String message = "Stock no disponible";

        // When
        ActualizarStockException exception = assertThrows(ActualizarStockException.class, () -> {
            throw new ActualizarStockException(message);
        });

        // Then
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testActualizarStockExceptionMessageAndCause() {
        // Given
        String message = "Stock no disponible";
        Throwable cause = new Throwable("Causa original");

        // When
        ActualizarStockException exception = assertThrows(ActualizarStockException.class, () -> {
            throw new ActualizarStockException(message, cause);
        });

        // Then
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
