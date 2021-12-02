package br.com.xbrain.teste.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Vendas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern ="dd/MM/yyyy")
    private LocalDate dataVenda;

    private Double valorVenda;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Vendas() {
        super();
    }

    public Vendas(Integer id, LocalDate dataVenda, Double valorVenda, Vendedor vendedor, Cliente cliente) {
        super();
        this.id = id;
        this.dataVenda = dataVenda;
        this.valorVenda = valorVenda;
        this.vendedor = vendedor;
        this.cliente = cliente;

    }


}
