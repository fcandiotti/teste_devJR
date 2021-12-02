package br.com.xbrain.teste.domain.dto;

import br.com.xbrain.teste.domain.Vendedor;
import br.com.xbrain.teste.domain.enums.Perfil;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
public class VendedorDTO implements Serializable {

    protected Integer id;


    @NotNull
    protected String nome;

    public Double totalVendas;

    public Integer quantidadeVenda;

    public Stream<String> informacoesDasVendas;

    public OptionalDouble mediaVendas;

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

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

}
