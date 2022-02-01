package br.com.xbrain.teste.controller;


import br.com.xbrain.teste.domain.Vendas;
import br.com.xbrain.teste.domain.dto.VendasDTO;
import br.com.xbrain.teste.services.VendasService;
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
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendasService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendasDTO create(@Valid @RequestBody VendasDTO vendasDTO) {
        Vendas novaVenda = service.create(vendasDTO);
        return new VendasDTO(novaVenda);
    }

    @GetMapping
    public List<VendasDTO> findAll() {
        List<Vendas> listaVendas = service.findAll();
        return listaVendas.stream().map(VendasDTO::new).collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public VendasDTO findById(@PathVariable Integer id) {
        Vendas listaVenda = service.findById(id);
        return new VendasDTO(listaVenda);
    }

    @PutMapping("/{id}")
    public VendasDTO update(@PathVariable Integer id, @Valid @RequestBody VendasDTO vendasDTO) {
        Vendas atualizaVenda = service.update(id, vendasDTO);
        return new VendasDTO(atualizaVenda);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}

