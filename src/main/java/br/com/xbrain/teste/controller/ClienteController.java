package br.com.xbrain.teste.controller;

import br.com.xbrain.teste.domain.Cliente;
import br.com.xbrain.teste.domain.dto.ClienteDTO;
import br.com.xbrain.teste.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    public ClienteDTO findById(@PathVariable Integer id) {
        Cliente clienteDTO = service.findById(id);
        return new ClienteDTO(clienteDTO);
    }

    @GetMapping
    public List<ClienteDTO> findAll() {
        List<Cliente> listaClientes = service.findAll();
        return listaClientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO create(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente novoCliente = service.save(clienteDTO);
        return new ClienteDTO(novoCliente);
    }
}
