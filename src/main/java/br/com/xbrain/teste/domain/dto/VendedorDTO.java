package br.com.xbrain.teste.domain.dto;

import br.com.xbrain.teste.domain.Vendedor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Stream;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VendedorDTO implements Serializable {

    private Integer id;

    private String nome;

    private Double totalVendas;

    private Integer quantidadeVenda;

    private Stream<String> informacoesDasVendas;

    private OptionalDouble mediaVendas;

    private OptionalDouble mediaVendasPorDia;


    @JsonIgnore
    protected Set<Integer> perfis = new HashSet<>();

    public VendedorDTO() {
        super();
    }

    public VendedorDTO(Vendedor obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.quantidadeVenda = obj.quantidaDeVendas();
        this.totalVendas = obj.totalVendas();
        this.informacoesDasVendas = obj.informacoesDasVendas();
        this.mediaVendas = obj.mediaVendas();
    }

    public VendedorDTO(Vendedor obj, LocalDate dataInicio, LocalDate dataFim) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.quantidadeVenda = obj.quantidaDeVendas();
        this.totalVendas = obj.totalVendas();
        this.informacoesDasVendas = obj.informacoesDasVendas();
        this.mediaVendas = obj.mediaVendas();
        this.mediaVendasPorDia = obj.mediaDeVendasPorDia(dataInicio, dataFim);
    }
 }

