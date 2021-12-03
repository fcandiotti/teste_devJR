package br.com.xbrain.teste.domain.dto;

import br.com.xbrain.teste.domain.Vendedor;
import br.com.xbrain.teste.domain.enums.Perfil;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
public class VendedorDTO implements Serializable {

    private Integer id;

    private String nome;

    private Double totalVendas;

    private Integer quantidadeVenda;

    private Stream<String> informacoesDasVendas;

    private OptionalDouble mediaVendas;

    private OptionalDouble mediaVendasPorDia;


    protected Set<Integer> perfis = new HashSet<>();

    public VendedorDTO() {
        super();
        addPerfil(Perfil.VENDEDOR);
    }

    public VendedorDTO(Vendedor obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.quantidadeVenda = obj.quantidaDeVendas();
        this.totalVendas = obj.totalVendas();
        this.informacoesDasVendas = obj.informacoesDasVendas();
        this.mediaVendas = obj.mediaVendas();
    }

    public VendedorDTO(Vendedor obj, LocalDate dataInicio, LocalDate dataFim) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.perfis = obj.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.quantidadeVenda = obj.quantidaDeVendas();
        this.totalVendas = obj.totalVendas();
        this.informacoesDasVendas = obj.informacoesDasVendas();
        this.mediaVendas = obj.mediaVendas();
        this.mediaVendasPorDia = obj.mediaDeVendasPorDia(dataInicio, dataFim);
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }




}
