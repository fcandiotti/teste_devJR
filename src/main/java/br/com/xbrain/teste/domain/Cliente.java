package br.com.xbrain.teste.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Pessoa {

    private List<Vendas> vendas = new ArrayList<>();


}
