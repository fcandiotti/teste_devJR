package br.com.xbrain.teste.services;

import br.com.xbrain.teste.domain.Cliente;
import br.com.xbrain.teste.domain.dto.ClienteDTO;
import br.com.xbrain.teste.repositories.ClienteRepository;
import br.com.xbrain.teste.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = repository.findById(id);
                return cliente.orElseThrow(() -> new ObjectnotFoundException("Cliente Não Encontrado: "+ id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente save(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        Cliente novoCliente = new Cliente(clienteDTO);
        return repository.save((novoCliente));
    }
}
