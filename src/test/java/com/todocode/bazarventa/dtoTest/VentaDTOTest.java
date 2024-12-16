package com.todocode.bazarventa.dtoTest;

import com.todocode.bazarventa.dto.VentaDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class VentaDTOTest {

    @Test
    void testVentaDTOConstructor() {
        VentaDTO ventaDTO = new VentaDTO();
        assertNull(ventaDTO.getCodigo());
        assertNull(ventaDTO.getFecha());
        assertNull(ventaDTO.getTotal());
    }

    @Test
    void testVentaDTOConstructorWithParameters() {
        Long codigo = 1L;
        LocalDate fecha = LocalDate.now();
        Double total = 100.0;

        VentaDTO ventaDTO = new VentaDTO(codigo, fecha, total);
        assertEquals(codigo, ventaDTO.getCodigo());
        assertEquals(fecha, ventaDTO.getFecha());
        assertEquals(total, ventaDTO.getTotal());
    }

    @Test
    void testSetCodigo() {
        VentaDTO ventaDTO = new VentaDTO();
        Long codigo = 1L;
        ventaDTO.setCodigo(codigo);
        assertEquals(codigo, ventaDTO.getCodigo());
    }

    @Test
    void testSetFecha() {
        VentaDTO ventaDTO = new VentaDTO();
        LocalDate fecha = LocalDate.now();
        ventaDTO.setFecha(fecha);
        assertEquals(fecha, ventaDTO.getFecha());
    }

    @Test
    void testSetTotal() {
        VentaDTO ventaDTO = new VentaDTO();
        Double total = 100.0;
        ventaDTO.setTotal(total);
        assertEquals(total, ventaDTO.getTotal());
    }
}



