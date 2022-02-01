package br.com.xbrain.teste.services;

import br.com.xbrain.teste.domain.Cliente;
import br.com.xbrain.teste.domain.Vendas;
import br.com.xbrain.teste.domain.Vendedor;
import br.com.xbrain.teste.domain.dto.VendasDTO;
import br.com.xbrain.teste.repositories.VendasRepository;
import br.com.xbrain.teste.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class VendasService {

    @Autowired
    private VendasRepository repository;
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private ClienteService clienteService;

    public Vendas findById(Integer id) {
        Optional<Vendas> ListaVenda = repository.findById(id);
        return ListaVenda.orElseThrow(() -> new ObjectnotFoundException("Venda Não Encontrada! " + id));
    }

    public List<Vendas> findAll() {
        return repository.findAll();
    }

    public Vendas create(@Valid VendasDTO vendasDTO) {
        return repository.save(newVendas(vendasDTO));
    }

    public Vendas update(Integer id, @Valid VendasDTO vendasDTO) {
        vendasDTO.setId(id);
        Vendas vendaAtualizada = findById(id);
        vendaAtualizada = newVendas(vendasDTO);
        return repository.save(vendaAtualizada);
    }

    public void delete(Integer id) {
        Vendas obj = findById(id);
        repository.deleteById(id);
    }

    private Vendas newVendas(VendasDTO vendasDTO) {
        Vendedor vendedor = vendedorService.findById(vendasDTO.getVendedor());
        Cliente cliente = clienteService.findById(vendasDTO.getCliente());

        Vendas venda = new Vendas();
        if(vendasDTO.getId() !=null ) {
            venda.setId(vendasDTO.getId());
        }

        venda.setVendedor(vendedor);
        venda.setCliente(cliente);
        venda.setValorVenda(vendasDTO.getValorVenda());
        venda.setDataVenda(vendasDTO.getDataVenda());
        return venda;
    }





}
