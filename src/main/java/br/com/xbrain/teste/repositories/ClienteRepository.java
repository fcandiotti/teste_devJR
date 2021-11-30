package br.com.xbrain.teste.repositories;

import br.com.xbrain.teste.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
