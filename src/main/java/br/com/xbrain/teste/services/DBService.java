package br.com.xbrain.teste.services;

import br.com.xbrain.teste.domain.Cliente;
import br.com.xbrain.teste.domain.Vendas;
import br.com.xbrain.teste.domain.Vendedor;
import br.com.xbrain.teste.repositories.ClienteRepository;
import br.com.xbrain.teste.repositories.VendasRepository;
import br.com.xbrain.teste.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VendasRepository vendasRepository;

    public void instanciaDB() {

        Vendedor vendedor1 = new Vendedor(null, "Fernando Candiotti");
        Vendedor vendedor2 = new Vendedor(null, "Pedro Bino");

        Cliente cli1 = new Cliente(null, "José da Silva");
        Cliente cli2 = new Cliente(null, "João Mineiro");

        Vendas v1 = new Vendas(null,100.10,LocalDate.of(2021, 11, 12),vendedor1,cli1);
        Vendas v2 = new Vendas(null,220.15,LocalDate.of(2021,11,13),vendedor1,cli1);
        Vendas v3 = new Vendas(null,345.80,LocalDate.of(2021,11,12),vendedor2,cli2);
        Vendas v4 = new Vendas(null,100.45,LocalDate.of(2021,11,13),vendedor2,cli2);;

        vendedorRepository.saveAll(Arrays.asList(vendedor1, vendedor2));
        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        vendasRepository.saveAll(Arrays.asList(v1, v2, v3, v4));

    }

}
