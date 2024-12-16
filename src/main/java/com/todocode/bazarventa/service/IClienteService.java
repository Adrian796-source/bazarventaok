
package com.todocode.bazarventa.service;

import com.todocode.bazarventa.dto.ClienteDTO;
import com.todocode.bazarventa.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    void saveCliente(Cliente cliente);

    List<Cliente> getClientes();

    List<ClienteDTO> getAllClientesDTO();

    Optional<Cliente> findByCodigoCliente(Long codigoCliente);

    void deleteCliente(Long codigoCliente);

    void editCliente(Cliente cliente);


}
