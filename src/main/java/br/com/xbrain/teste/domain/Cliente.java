package br.com.xbrain.teste.domain;

import br.com.xbrain.teste.domain.dto.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")

    private List<Vendas> vendas = new ArrayList<>();

    public Cliente() {
        super();
    }

    public Cliente(Integer id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public Cliente(ClienteDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

}
