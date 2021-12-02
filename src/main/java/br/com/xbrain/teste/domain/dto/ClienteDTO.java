package br.com.xbrain.teste.domain.dto;

import br.com.xbrain.teste.domain.Cliente;
import br.com.xbrain.teste.domain.enums.Perfil;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Data
public class ClienteDTO implements Serializable {

    protected Integer id;

    @NotNull(message = "O Campo NOME é obrigatório!")
    protected String nome;
    protected Set<Integer> perfis = new HashSet<>();

    public ClienteDTO() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public ClienteDTO(Cliente obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());

    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

}
