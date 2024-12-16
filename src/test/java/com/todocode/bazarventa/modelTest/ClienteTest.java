package com.todocode.bazarventa.modelTest;

import com.todocode.bazarventa.model.Cliente;
import com.todocode.bazarventa.model.Venta;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    @Test
    void clienteConstructorWithParametersTest() {
        // Given
        Long codigoCliente = 1L;
        String nombre = "Juan";
        String apellido = "Perez";
        String dni = "12345678";
        List<Venta> ventas = List.of(new Venta());

        // When
        Cliente cliente = new Cliente(codigoCliente, nombre, apellido, dni, ventas);

        // Then
        assertEquals(codigoCliente, cliente.getCodigoCliente());
        assertEquals(nombre, cliente.getNombre());
        assertEquals(apellido, cliente.getApellido());
        assertEquals(dni, cliente.getDni());
        assertEquals(ventas, cliente.getVenta());
    }

    @Test
    void setGetVentaTest() {
        // Given
        Cliente cliente = new Cliente();
        List<Venta> ventas = List.of(new Venta());

        // When
        cliente.setVenta(ventas);

        // Then
        assertEquals(ventas, cliente.getVenta());
    }

}
