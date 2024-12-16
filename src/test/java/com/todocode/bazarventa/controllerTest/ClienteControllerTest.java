package com.todocode.bazarventa.controllerTest;


import com.todocode.bazarventa.controller.ClienteController;
import com.todocode.bazarventa.dto.ClienteDTO;
import com.todocode.bazarventa.model.Cliente;
import com.todocode.bazarventa.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {


    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    void deleteClienteTest() {
        // Given
        Long clienteId = 1L;
        doNothing().when(clienteService).deleteCliente(clienteId);

        // When
        clienteController.deleteCliente(clienteId);

        // Then
        verify(clienteService).deleteCliente(clienteId);
    }

    @Test
    void saveClienteTest() {
        // Given
        Cliente newCliente = new Cliente();
        newCliente.setNombre("Juan Perez");
        newCliente.setApellido("Perez");
        newCliente.setDni("12345678");
        doNothing().when(clienteService).saveCliente(any(Cliente.class));

        // When
        clienteController.saveCliente(newCliente);

        // Then
        verify(clienteService).saveCliente(newCliente);

    }

    @Test
    void getAllClientesDTOTest() {
        // Given
        List<ClienteDTO> clientes = Arrays.asList(new ClienteDTO(1L, "Juan", "Perez", "12345678"));
        when(clienteService.getAllClientesDTO()).thenReturn(clientes);

        // When
        List<ClienteDTO> result = clienteController.getAllClientesDTO();

        // Then
        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getNombre());
    }

    @Test
    void findClientePorCodigoTest() {
        // Given
        Long codigoCliente = 1L;
        Cliente cliente = new Cliente(codigoCliente, "Juan", "Perez", "12345678");
        when(clienteService.findByCodigoCliente(codigoCliente)).thenReturn(Optional.of(cliente));

        // When
        ClienteDTO result = clienteController.findByCodigoCliente(codigoCliente);

        // Then
        assertEquals(codigoCliente, result.getCodigoCliente());
        assertEquals("Juan", result.getNombre());
        assertEquals("Perez", result.getApellido());
        assertEquals("12345678", result.getDni());

    }

    @Test
    void findByCodigoClienteNotFoundTest() {
        // Given
        Long codigoCliente = 1L;
        when(clienteService.findByCodigoCliente(codigoCliente)).thenReturn(Optional.empty());

        // When
        ClienteDTO result = clienteController.findByCodigoCliente(codigoCliente);

        // Then
        ClienteDTO expected = new ClienteDTO();
        assertEquals(expected.getCodigoCliente(), result.getCodigoCliente());
        assertEquals(expected.getNombre(), result.getNombre());
        assertEquals(expected.getApellido(), result.getApellido());
        assertEquals(expected.getDni(), result.getDni());
    }


    @Test
    void editarClienteNotFoundTest() {
        // Given
        Long codigoCliente = 1L;
        Cliente cliente = new Cliente(codigoCliente, "Juan", "Perez", "12345678");
        when(clienteService.findByCodigoCliente(codigoCliente)).thenReturn(Optional.empty());

        // When
        clienteController.editarCliente(codigoCliente, cliente);

        // Then
        verify(clienteService, never()).saveCliente(any(Cliente.class));
    }


}
