package com.todocode.bazarventa.dtoTest;

import com.todocode.bazarventa.dto.ProductoDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductoDTOTest {


    @Test
    void productoDTOConstructorTest() {
        ProductoDTO productoDTO = new ProductoDTO();
        assertNull(productoDTO.getCodigoProducto());
        assertNull(productoDTO.getNombre());
        assertNull(productoDTO.getMarca());
        assertNull(productoDTO.getCosto());
        assertEquals(0, productoDTO.getCantidadDisponible());
    }

    @Test
    void productoDTOConstructorWithParametersTest() {
        Long codigoProducto = 1L;
        String nombre = "Producto 1";
        String marca = "Marca 1";
        Double costo = 100.0;
        int cantidadDisponible = 10;

        ProductoDTO productoDTO = new ProductoDTO(codigoProducto, nombre, marca, costo, cantidadDisponible);
        assertEquals(codigoProducto, productoDTO.getCodigoProducto());
        assertEquals(nombre, productoDTO.getNombre());
        assertEquals(marca, productoDTO.getMarca());
        assertEquals(costo, productoDTO.getCosto());
        assertEquals(cantidadDisponible, productoDTO.getCantidadDisponible());
    }

    @Test
    void setCodigoProductoTest() {
        ProductoDTO productoDTO = new ProductoDTO();
        Long codigoProducto = 1L;
        productoDTO.setCodigoProducto(codigoProducto);
        assertEquals(codigoProducto, productoDTO.getCodigoProducto());
    }

    @Test
    void setNombreTest() {
        ProductoDTO productoDTO = new ProductoDTO();
        String nombre = "Producto 1";
        productoDTO.setNombre(nombre);
        assertEquals(nombre, productoDTO.getNombre());
    }

    @Test
    void setMarcaTest() {
        ProductoDTO productoDTO = new ProductoDTO();
        String marca = "Marca 1";
        productoDTO.setMarca(marca);
        assertEquals(marca, productoDTO.getMarca());
    }

    @Test
    void testSetCosto() {
        ProductoDTO productoDTO = new ProductoDTO();
        Double costo = 100.0;
        productoDTO.setCosto(costo);
        assertEquals(costo, productoDTO.getCosto());
    }

    @Test
    void testSetCantidadDisponible() {
        ProductoDTO productoDTO = new ProductoDTO();
        int cantidadDisponible = 10;
        productoDTO.setCantidadDisponible(cantidadDisponible);
        assertEquals(cantidadDisponible, productoDTO.getCantidadDisponible());
    }

}
