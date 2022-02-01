package br.com.xbrain.teste.controller;

import br.com.xbrain.teste.domain.Cliente;
import br.com.xbrain.teste.domain.dto.ClienteDTO;
import br.com.xbrain.teste.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    public ClienteDTO findById(@PathVariable Integer id) {
        Cliente obj = service.findById(id);
        return new ClienteDTO(obj);
    }

    @GetMapping
    public List<ClienteDTO> findAll() {
        List<Cliente> listaClientes = service.findAll();
        return listaClientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO create(@Valid @RequestBody ClienteDTO objDTO) {
        Cliente newObj = service.save(objDTO);
        return new ClienteDTO(newObj);
    }
}
