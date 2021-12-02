package br.com.xbrain.teste.repositories;

import br.com.xbrain.teste.domain.Vendedor;
import br.com.xbrain.teste.domain.dto.VendedorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

    @Query("SELECT DISTINCT v FROM Vendedor v LEFT JOIN FETCH v.vendas ve WHERE ve.dataVenda BETWEEN ?1 AND ?2")
    List<VendedorDTO> filtraVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
}
