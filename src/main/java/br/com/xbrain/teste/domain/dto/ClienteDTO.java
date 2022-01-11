package br.com.xbrain.teste.domain.dto;

import br.com.xbrain.teste.domain.Cliente;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Data
public class ClienteDTO implements Serializable {

    private Integer id;

    @NotNull(message = "O Campo NOME é obrigatório!")
    private String nome;

    private Set<Integer> perfis = new HashSet<>();

    public ClienteDTO() {
        super();
    }

    public ClienteDTO(Cliente obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

}
