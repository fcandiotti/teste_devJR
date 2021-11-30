package br.com.xbrain.teste.repositories;

import br.com.xbrain.teste.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
