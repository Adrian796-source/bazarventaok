package com.todocode.bazarventa.dtoTest;

import com.todocode.bazarventa.dto.ClienteDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClienteDTOTest {

    @Test
    void clienteDTOConstructorTest() {
        ClienteDTO clienteDTO = new ClienteDTO();
        assertNull(clienteDTO.getCodigoCliente());
        assertNull(clienteDTO.getNombre());
        assertNull(clienteDTO.getApellido());
        assertNull(clienteDTO.getDni());
    }

    @Test
    void clienteDTOConstructorWithParametersTest() {
        Long codigoCliente = 1L;
        String nombre = "Juan";
        String apellido = "Perez";
        String dni = "12345678";

        ClienteDTO clienteDTO = new ClienteDTO(codigoCliente, nombre, apellido, dni);
        assertEquals(codigoCliente, clienteDTO.getCodigoCliente());
        assertEquals(nombre, clienteDTO.getNombre());
        assertEquals(apellido, clienteDTO.getApellido());
        assertEquals(dni, clienteDTO.getDni());
    }

    @Test
    void setCodigoClienteTest() {
        ClienteDTO clienteDTO = new ClienteDTO();
        Long codigoCliente = 1L;
        clienteDTO.setCodigoCliente(codigoCliente);
        assertEquals(codigoCliente, clienteDTO.getCodigoCliente());
    }

    @Test
    void setNombreTest() {
        ClienteDTO clienteDTO = new ClienteDTO();
        String nombre = "Juan";
        clienteDTO.setNombre(nombre);
        assertEquals(nombre, clienteDTO.getNombre());
    }

    @Test
    void setApellidoTest() {
        ClienteDTO clienteDTO = new ClienteDTO();
        String apellido = "Perez";
        clienteDTO.setApellido(apellido);
        assertEquals(apellido, clienteDTO.getApellido());
    }

    @Test
    void setDniTest() {
        ClienteDTO clienteDTO = new ClienteDTO();
        String dni = "12345678";
        clienteDTO.setDni(dni);
        assertEquals(dni, clienteDTO.getDni());
    }

}
