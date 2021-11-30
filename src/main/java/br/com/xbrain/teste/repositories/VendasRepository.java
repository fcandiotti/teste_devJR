package br.com.xbrain.teste.repositories;

import br.com.xbrain.teste.domain.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendasRepository extends JpaRepository<Vendas, Integer> {
}
