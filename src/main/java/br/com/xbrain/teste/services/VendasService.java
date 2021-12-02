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
        Optional<Vendas> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Venda Não Encontrada! " + id));
    }

    public List<Vendas> findAll() {
        return repository.findAll();
    }

    public Vendas create(@Valid VendasDTO objDTO) {
        return repository.save(newVendas(objDTO));
    }

    public Vendas update(Integer id, @Valid VendasDTO objDTO) {
        objDTO.setId(id);
        Vendas oldObj = findById(id);
        oldObj = newVendas(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Vendas obj = findById(id);
        repository.deleteById(id);
    }

    private Vendas newVendas(VendasDTO obj) {
        Vendedor vendedor = vendedorService.findById(obj.getVendedor());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Vendas venda = new Vendas();
        if(obj.getId() !=null ) {
            venda.setId(obj.getId());
        }

        venda.setVendedor(vendedor);
        venda.setCliente(cliente);
        venda.setValorVenda(obj.getValorVenda());
        venda.setDataVenda(obj.getDataVenda());
        return venda;
    }





}
