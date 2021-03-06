package br.com.xbrain.teste.domain;

import br.com.xbrain.teste.domain.dto.VendedorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;

@Data
@Entity
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "vendedor")
    private List<Vendas> vendas = new ArrayList<>();


    public Vendedor() {
    }

    public Vendedor(Integer id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public Vendedor(VendedorDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public int quantidaDeVendas() {
        return vendas.size();
    }

    public Stream<String> informacoesDasVendas() {
        return vendas.stream()
                .map(vendas -> vendas.getDataVenda().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " Valor Venda: " + vendas.getValorVenda());
    }

    public OptionalDouble mediaVendas(){
        return vendas.stream()
                .map(Vendas::getValorVenda)
                .mapToDouble(Double::doubleValue)
                .average();
    }


    public Double totalVendas() {
        return vendas.stream()
                .map(Vendas::getValorVenda)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public OptionalDouble mediaDeVendasPorDia(LocalDate dataInicio, LocalDate dataFim) {
        Long totalDeDias = DAYS.between(dataInicio, dataFim) + 1;
        return OptionalDouble.of((double) quantidaDeVendas() / totalDeDias);
    }

}
