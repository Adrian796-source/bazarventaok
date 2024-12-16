package com.todocode.bazarventa.serviceTest;

import com.todocode.bazarventa.dto.ClienteDTO;
import com.todocode.bazarventa.model.Cliente;
import com.todocode.bazarventa.repository.IClienteRepository;
import com.todocode.bazarventa.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private IClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void testSaveCliente() {
        // Given
        Long codigoCliente = 1L;
        Cliente cliente = new Cliente(codigoCliente, "Juan", "Perez", "12345678");
        when(clienteRepository.findByCodigoCliente(codigoCliente)).thenReturn(Optional.of(cliente));

        // When
        Optional<Cliente> result = clienteService.findByCodigoCliente(codigoCliente);

        // Then
        assertEquals(codigoCliente, result.get().getCodigoCliente());
        assertEquals("Juan", result.get().getNombre());
        assertEquals("Perez", result.get().getApellido());
        assertEquals("12345678", result.get().getDni());
    }


    @Test
    void testGetClientes() {
        // Given
        List<Cliente> clientes = List.of(new Cliente(), new Cliente());

        when(clienteRepository.findAll()).thenReturn(clientes);

        // When
        List<Cliente> result = clienteService.getClientes();

        // Then
        assertEquals(clientes, result);
    }


    @Test
    void testGetAllClientesDTO() {
        // Given
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(1L);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setDni("12345678");

        List<Cliente> clientes = List.of(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);

        // When
        List<ClienteDTO> result = clienteService.getAllClientesDTO();

        // Then
        assertEquals(1, result.size());
        ClienteDTO dto = result.get(0);
        assertEquals(cliente.getCodigoCliente(), dto.getCodigoCliente());
        assertEquals(cliente.getNombre(), dto.getNombre());
        assertEquals(cliente.getApellido(), dto.getApellido());
        assertEquals(cliente.getDni(), dto.getDni());
    }

    @Test
    void testFindByCodigoCliente() {
        // Given
        Long codigoCliente = 1L;
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(codigoCliente);

        when(clienteRepository.findByCodigoCliente(anyLong())).thenReturn(Optional.of(cliente));

        // When
        Optional<Cliente> result = clienteService.findByCodigoCliente(codigoCliente);

        // Then
        assertEquals(cliente, result.get());
    }

    @Test
    void testDeleteCliente() {
        // Given
        Long codigoCliente = 1L;

        // When
        clienteService.deleteCliente(codigoCliente);

        // Then
        verify(clienteRepository, times(1)).deleteById(codigoCliente);
    }

    @Test
    void testEditCliente() {
        // Given
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(1L);

        // When
        clienteService.editCliente(cliente);

        // Then
        verify(clienteRepository, times(1)).save(cliente);
    }

}



