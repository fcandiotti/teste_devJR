package br.com.xbrain.teste.services;

import br.com.xbrain.teste.domain.Vendas;
import br.com.xbrain.teste.repositories.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendasService {

    @Autowired
    private VendasRepository vendasRepository;

    public Vendas salvar(Vendas venda) {
        return vendasRepository.save(venda);
    }

    public List<Vendas> listaVendas() {
        return vendasRepository.findAll();
    }

    public Optional<Vendas> buscarPorId(Integer id) {
        return vendasRepository.findById(id);
    }

    public void removeVendaPorId(Integer id) {
        vendasRepository.deleteById(id);
    }



}
