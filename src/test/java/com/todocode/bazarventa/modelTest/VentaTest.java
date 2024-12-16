package com.todocode.bazarventa.modelTest;

import com.todocode.bazarventa.model.Cliente;
import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.model.Venta;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VentaTest {

    @Test
    void ventaConstructorWithParametersTest() {
        // Given
        Long codigo = 1L;
        LocalDate fecha = LocalDate.now();
        Double total = 100.0;
        List<Producto> listaProductos = List.of(new Producto());
        Cliente unCliente = new Cliente();

        // When
        Venta venta = new Venta(codigo, fecha, total, listaProductos, unCliente);

        // Then
        assertEquals(codigo, venta.getCodigo());
        assertEquals(fecha, venta.getFecha());
        assertEquals(total, venta.getTotal());
        assertEquals(listaProductos, venta.getListaProductos());
        assertEquals(unCliente, venta.getUnCliente());
    }

}
