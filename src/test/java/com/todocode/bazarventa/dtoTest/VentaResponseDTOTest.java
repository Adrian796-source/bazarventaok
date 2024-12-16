package com.todocode.bazarventa.dtoTest;

import com.todocode.bazarventa.dto.VentaResponseDTO;
import com.todocode.bazarventa.model.Producto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class VentaResponseDTOTest {


    @Test
    void testVentaResponseDTOConstructor() {
        VentaResponseDTO ventaResponseDTO = new VentaResponseDTO();
        assertNull(ventaResponseDTO.getCodigo());
        assertNull(ventaResponseDTO.getFecha());
        assertNull(ventaResponseDTO.getTotal());
        assertNull(ventaResponseDTO.getListaProductos());
        assertNull(ventaResponseDTO.getNombre());
        assertNull(ventaResponseDTO.getApellido());
        assertNull(ventaResponseDTO.getDni());
    }

    @Test
    void testVentaResponseDTOConstructorWithParameters() {
        Long codigo = 1L;
        LocalDate fecha = LocalDate.now();
        Double total = 100.0;
        List<Producto> listaProductos = List.of(new Producto());
        String nombre = "Juan";
        String apellido = "Perez";
        String dni = "12345678";

        VentaResponseDTO ventaResponseDTO = new VentaResponseDTO(codigo, fecha, total, listaProductos, nombre, apellido, dni);
        assertEquals(codigo, ventaResponseDTO.getCodigo());
        assertEquals(fecha, ventaResponseDTO.getFecha());
        assertEquals(total, ventaResponseDTO.getTotal());
        assertEquals(listaProductos, ventaResponseDTO.getListaProductos());
        assertEquals(nombre, ventaResponseDTO.getNombre());
        assertEquals(apellido, ventaResponseDTO.getApellido());
        assertEquals(dni, ventaResponseDTO.getDni());
    }

    @Test
    void testSetCodigo() {
        VentaResponseDTO ventaResponseDTO = new VentaResponseDTO();
        Long codigo = 1L;
        ventaResponseDTO.setCodigo(codigo);
        assertEquals(codigo, ventaResponseDTO.getCodigo());
    }

    @Test
    void testSetFecha() {
        VentaResponseDTO ventaResponseDTO = new VentaResponseDTO();
        LocalDate fecha = LocalDate.now();
        ventaResponseDTO.setFecha(fecha);
        assertEquals(fecha, ventaResponseDTO.getFecha());
    }

    @Test
    void testSetTotal() {
        VentaResponseDTO ventaResponseDTO = new VentaResponseDTO();
        Double total = 100.0;
        ventaResponseDTO.setTotal(total);
        assertEquals(total, ventaResponseDTO.getTotal());
    }

    @Test
    void testSetListaProductos() {
        VentaResponseDTO ventaResponseDTO = new VentaResponseDTO();
        List<Producto> listaProductos = List.of(new Producto());
        ventaResponseDTO.setListaProductos(listaProductos);
        assertEquals(listaProductos, ventaResponseDTO.getListaProductos());
    }

    @Test
    void testSetNombre() {
        VentaResponseDTO ventaResponseDTO = new VentaResponseDTO();
        String nombre = "Juan";
        ventaResponseDTO.setNombre(nombre);
        assertEquals(nombre, ventaResponseDTO.getNombre());
    }

    @Test
    void testSetApellido() {
        VentaResponseDTO ventaResponseDTO = new VentaResponseDTO();
        String apellido = "Perez";
        ventaResponseDTO.setApellido(apellido);
        assertEquals(apellido, ventaResponseDTO.getApellido());
    }

    @Test
    void testSetDni() {
        VentaResponseDTO ventaResponseDTO = new VentaResponseDTO();
        String dni = "12345678";
        ventaResponseDTO.setDni(dni);
        assertEquals(dni, ventaResponseDTO.getDni());
    }

}
