package br.com.xbrain.teste.controller;


import br.com.xbrain.teste.domain.Vendas;
import br.com.xbrain.teste.domain.dto.VendasDTO;
import br.com.xbrain.teste.services.VendasService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<VendasDTO> create(@Valid @RequestBody VendasDTO objDTO) {
        Vendas obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<VendasDTO>> findAll() {
        List<Vendas> list = service.findAll();
        List<VendasDTO> listDTO = list.stream().map(obj -> new VendasDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendasDTO> findById(@PathVariable Integer id) {
        Vendas obj = service.findById(id);
        return ResponseEntity.ok().body(new VendasDTO(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendasDTO> update(@PathVariable Integer id, @Valid @RequestBody VendasDTO objDTO) {
        Vendas newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new VendasDTO(newObj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VendasDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

