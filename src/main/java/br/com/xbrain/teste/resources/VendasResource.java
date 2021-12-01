package br.com.xbrain.teste.resources;


import br.com.xbrain.teste.domain.Vendas;
import br.com.xbrain.teste.services.VendasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendasResource {

    @Autowired
    private VendasService vendasService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendas salvar(@RequestBody Vendas venda) {
        return vendasService.salvar(venda);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Vendas> listaVendas() {
        return vendasService.listaVendas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vendas buscarVendaPorId(@PathVariable("id") Integer id) {
        return vendasService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeVendaPorId(@PathVariable("id") Integer id) {
        vendasService.buscarPorId(id)
                .map(vendas -> {
                    vendasService.removeVendaPorId(vendas.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarVenda(@PathVariable("id") Integer id, @RequestBody Vendas venda) {
        vendasService.buscarPorId(id)
                .map(baseVendas -> {
                    modelMapper.map(venda, baseVendas);
                    vendasService.salvar(baseVendas);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venda não encontrada"));
    }

}

