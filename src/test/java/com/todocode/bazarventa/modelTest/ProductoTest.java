package com.todocode.bazarventa.modelTest;

import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.model.Venta;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductoTest {

    @Test
    void productoConstructorWithParametersTest() {
        // Given
        Long codigoProducto = 1L;
        String nombre = "Producto 1";
        String marca = "Marca 1";
        Double costo = 100.0;
        int cantidadDisponible = 10;
        List<Venta> ventas = List.of(new Venta());

        // When
        Producto producto = new Producto(codigoProducto, nombre, marca, costo, cantidadDisponible, ventas);

        // Then
        assertEquals(codigoProducto, producto.getCodigoProducto());
        assertEquals(nombre, producto.getNombre());
        assertEquals(marca, producto.getMarca());
        assertEquals(costo, producto.getCosto());
        assertEquals(cantidadDisponible, producto.getCantidadDisponible());
        assertEquals(ventas, producto.getVenta());
    }

    @Test
    void setGetVentaTest() {
        // Given
        Producto producto = new Producto();
        List<Venta> ventas = List.of(new Venta());

        // When
        producto.setVenta(ventas);

        // Then
        assertEquals(ventas, producto.getVenta());
    }

}
