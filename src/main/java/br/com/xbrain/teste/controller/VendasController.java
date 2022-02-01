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
    public VendasDTO create(@Valid @RequestBody VendasDTO objDTO) {
        Vendas obj = service.create(objDTO);
        return new VendasDTO(obj);
    }

    @GetMapping
    public List<VendasDTO> findAll() {
        List<Vendas> list = service.findAll();
        return list.stream().map(VendasDTO::new).collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public VendasDTO findById(@PathVariable Integer id) {
        Vendas obj = service.findById(id);
        return new VendasDTO(obj);
    }

    @PutMapping("/{id}")
    public VendasDTO update(@PathVariable Integer id, @Valid @RequestBody VendasDTO objDTO) {
        Vendas newObj = service.update(id, objDTO);
        return new VendasDTO(newObj);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}

