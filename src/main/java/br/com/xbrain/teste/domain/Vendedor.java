package br.com.xbrain.teste.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vendedor extends Pessoa {

    private List<Vendas> vendas = new ArrayList<>();
}
