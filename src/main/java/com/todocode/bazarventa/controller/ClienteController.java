package com.todocode.bazarventa.controller;

import com.todocode.bazarventa.dto.ClienteDTO;

import com.todocode.bazarventa.model.Cliente;


import com.todocode.bazarventa.service.IClienteService;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService clienteService;


    @Autowired
    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;


    }

    // Crear un cliente
    @PostMapping("/crear")
    public void saveCliente(@RequestBody Cliente cliente) {
        clienteService.saveCliente(cliente);

    }

    // traer la lista de clientes
    @GetMapping
    public List<ClienteDTO> getAllClientesDTO() {
        return clienteService.getAllClientesDTO();

    }


    @GetMapping("/{codigoCliente}") // Busca el cliente por codigo
    public ClienteDTO findByCodigoCliente(@PathVariable Long codigoCliente) {

        // Obtener el cliente de la base de datos usando el servicio o el repositorio
        Optional<Cliente> client = clienteService.findByCodigoCliente(codigoCliente);

        // Verificar si se encontró el cliente
        if (client.isPresent()) {
            Cliente c = client.get();
            // Mapear los datos del cliente a ClienteDTO

            return new ClienteDTO(
                    c.getCodigoCliente(),
                    c.getNombre(),

                    c.getApellido(),
                    c.getDni()
            );
        } else {
            // Manejar el caso en el que no se encontró el cliente
            // Puedes devolver un DTO vacío o lanzar una excepción según tus necesidades.
            return new ClienteDTO();
        }

    }

    // Eliminar un cliente
    @DeleteMapping("/eliminar/{codigoCliente}")
    @Transactional
    public void deleteCliente(@PathVariable Long codigoCliente) {
        clienteService.deleteCliente(codigoCliente);

    }

    // Editar un cliente
    @PutMapping("/editar/{codigoCliente}")
    public void editarCliente(@PathVariable Long codigoCliente, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistenteOpt = clienteService.findByCodigoCliente(codigoCliente);

        if (clienteExistenteOpt.isPresent()) {
            Cliente clienteExistente = clienteExistenteOpt.get();
            // Actualiza los campos del cliente existente con los datos proporcionados
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setApellido(cliente.getApellido());
            clienteExistente.setDni(cliente.getDni());

            // llama al servicio para guardar el cliente actualizado
            clienteService.saveCliente(clienteExistente);

        }

    }

}
