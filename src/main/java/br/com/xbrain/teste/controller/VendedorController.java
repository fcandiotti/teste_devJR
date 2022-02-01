package br.com.xbrain.teste.controller;

import br.com.xbrain.teste.domain.Vendedor;
import br.com.xbrain.teste.domain.dto.VendedorDTO;
import br.com.xbrain.teste.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService service;

    @GetMapping("/{id}")
    public VendedorDTO findById(@PathVariable Integer id) {
        Vendedor obj = service.findById(id);
        return new VendedorDTO(obj);
    }

    @GetMapping
    public List<VendedorDTO> findAll() {
        List<Vendedor> list = service.findAll();
        return list.stream().map(VendedorDTO::new).collect(Collectors.toList());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendedorDTO create(@RequestBody VendedorDTO objDTO) {
        Vendedor newObj = service.create(objDTO);
        return new VendedorDTO(newObj);
    }

    @RequestMapping(value = "filtro")
    public List<VendedorDTO> filtraVendasPorPeriodo(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy")
                    LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd/MM/yyyy")
                    LocalDate dataFim
    ) {
        List<VendedorDTO> list = service.filtraVendasPorPeriodo(dataInicio, dataFim);
        return list;
    }

}
