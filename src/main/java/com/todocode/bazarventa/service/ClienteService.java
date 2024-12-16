
package com.todocode.bazarventa.service;

import com.todocode.bazarventa.dto.ClienteDTO;
import com.todocode.bazarventa.model.Cliente;
import com.todocode.bazarventa.repository.IClienteRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    private final IClienteRepository clienteRepository;

    @Autowired
    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);

    }

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public List<ClienteDTO> getAllClientesDTO() {
        return clienteRepository.findAll().stream()
                .map(cliente -> new ClienteDTO(cliente.getCodigoCliente(), cliente.getNombre(), cliente.getApellido(), cliente.getDni()))
                .toList();

    }

    @Override
    public Optional<Cliente> findByCodigoCliente(Long codigoCliente) {
        return clienteRepository.findByCodigoCliente(codigoCliente);
    }


    @Override
    public void deleteCliente(Long codigoCliente) {
        clienteRepository.deleteById(codigoCliente);


    }

    @Override
    public void editCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }


}
