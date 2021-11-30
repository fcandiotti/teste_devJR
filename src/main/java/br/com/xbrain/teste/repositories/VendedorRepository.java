package br.com.xbrain.teste.repositories;

import br.com.xbrain.teste.domain.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
}
