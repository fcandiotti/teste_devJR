package br.com.xbrain.teste.domain;

import br.com.xbrain.teste.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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
}
