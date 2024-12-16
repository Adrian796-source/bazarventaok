package com.todocode.bazarventa.dtoTest;

import com.todocode.bazarventa.dto.ProductoVentaDTO;
import com.todocode.bazarventa.model.Producto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductoVentaDTOTest {

    @Test
    void productoVentaDTOTest() {

        // Given
        Producto producto1 = new Producto();
        Producto producto2 = new Producto();
        List<Producto> listaProductos = List.of(producto1, producto2);

        // Cuando inicializamos la instancia con el constructor vacío y luego utilizamos los setters
        ProductoVentaDTO productoVentaDTO = new ProductoVentaDTO();
        productoVentaDTO.setCodigoVenta(1L);
        productoVentaDTO.setListaProductos(listaProductos);

        // Then - Comprobamos que los valores se recuperan correctamente
        assertEquals(1L, productoVentaDTO.getCodigoVenta());

        assertEquals(listaProductos, productoVentaDTO.getListaProductos());

    }

    @Test
    void productoVentaDTOConstructorTest() {
        ProductoVentaDTO productoVentaDTO = new ProductoVentaDTO();
        assertNull(productoVentaDTO.getCodigoVenta());
        assertNull(productoVentaDTO.getListaProductos());
    }

    @Test
    void productoVentaDTOConstructorWithParametersTest() {
        Long codigoVenta = 1L;
        List<Producto> listaProductos = List.of(new Producto());

        ProductoVentaDTO productoVentaDTO = new ProductoVentaDTO(codigoVenta, listaProductos);
        assertEquals(codigoVenta, productoVentaDTO.getCodigoVenta());
        assertEquals(listaProductos, productoVentaDTO.getListaProductos());
    }

    @Test
    void setCodigoVentaTest() {
        ProductoVentaDTO productoVentaDTO = new ProductoVentaDTO();
        Long codigoVenta = 1L;
        productoVentaDTO.setCodigoVenta(codigoVenta);
        assertEquals(codigoVenta, productoVentaDTO.getCodigoVenta());
    }

    @Test
    void setListaProductosTest() {
        ProductoVentaDTO productoVentaDTO = new ProductoVentaDTO();
        List<Producto> listaProductos = List.of(new Producto());
        productoVentaDTO.setListaProductos(listaProductos);
        assertEquals(listaProductos, productoVentaDTO.getListaProductos());
    }
}