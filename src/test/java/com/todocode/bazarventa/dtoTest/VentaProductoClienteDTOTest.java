package com.todocode.bazarventa.dtoTest;

import com.todocode.bazarventa.dto.VentaProductoClienteDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class VentaProductoClienteDTOTest {


    @Test
    void testVentaProductoClienteDTOConstructor() {
        VentaProductoClienteDTO ventaProductoClienteDTO = new VentaProductoClienteDTO();
        assertNull(ventaProductoClienteDTO.getCodigo());
        assertNull(ventaProductoClienteDTO.getTotal());
        assertEquals(0, ventaProductoClienteDTO.getCantidadDisponible());
        assertNull(ventaProductoClienteDTO.getNombre());
        assertNull(ventaProductoClienteDTO.getApellido());
    }

    @Test
    void testVentaProductoClienteDTOConstructorWithParameters() {
        Long codigo = 1L;
        Double total = 100.0;
        int cantidadDisponible = 10;
        String nombre = "Juan";
        String apellido = "Perez";

        VentaProductoClienteDTO ventaProductoClienteDTO = new VentaProductoClienteDTO(codigo, total, cantidadDisponible, nombre, apellido);
        assertEquals(codigo, ventaProductoClienteDTO.getCodigo());
        assertEquals(total, ventaProductoClienteDTO.getTotal());
        assertEquals(cantidadDisponible, ventaProductoClienteDTO.getCantidadDisponible());
        assertEquals(nombre, ventaProductoClienteDTO.getNombre());
        assertEquals(apellido, ventaProductoClienteDTO.getApellido());
    }

    @Test
    void testSetCodigo() {
        VentaProductoClienteDTO ventaProductoClienteDTO = new VentaProductoClienteDTO();
        Long codigo = 1L;
        ventaProductoClienteDTO.setCodigo(codigo);
        assertEquals(codigo, ventaProductoClienteDTO.getCodigo());
    }

    @Test
    void testSetTotal() {
        VentaProductoClienteDTO ventaProductoClienteDTO = new VentaProductoClienteDTO();
        Double total = 100.0;
        ventaProductoClienteDTO.setTotal(total);
        assertEquals(total, ventaProductoClienteDTO.getTotal());
    }

    @Test
    void testSetCantidadDisponible() {
        VentaProductoClienteDTO ventaProductoClienteDTO = new VentaProductoClienteDTO();
        int cantidadDisponible = 10;
        ventaProductoClienteDTO.setCantidadDisponible(cantidadDisponible);
        assertEquals(cantidadDisponible, ventaProductoClienteDTO.getCantidadDisponible());
    }

    @Test
    void testSetNombre() {
        VentaProductoClienteDTO ventaProductoClienteDTO = new VentaProductoClienteDTO();
        String nombre = "Juan";
        ventaProductoClienteDTO.setNombre(nombre);
        assertEquals(nombre, ventaProductoClienteDTO.getNombre());
    }

    @Test
    void testSetApellido() {
        VentaProductoClienteDTO ventaProductoClienteDTO = new VentaProductoClienteDTO();
        String apellido = "Perez";
        ventaProductoClienteDTO.setApellido(apellido);
        assertEquals(apellido, ventaProductoClienteDTO.getApellido());


    }
}