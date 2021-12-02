package br.com.xbrain.teste.domain;

import br.com.xbrain.teste.domain.dto.VendedorDTO;
import br.com.xbrain.teste.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Entity
public class Vendedor extends Pessoa {

    @JsonIgnore
    @OneToMany(mappedBy = "vendedor")
    private List<Vendas> vendas = new ArrayList<>();


    public Vendedor() {
        super();
        addPerfil(Perfil.VENDEDOR);
    }

    public Vendedor(Integer id, String nome) {
        super(id, nome);
        addPerfil(Perfil.VENDEDOR);
    }

    public Vendedor(VendedorDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
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
                .map(vendas -> vendas.getValorVenda())
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
