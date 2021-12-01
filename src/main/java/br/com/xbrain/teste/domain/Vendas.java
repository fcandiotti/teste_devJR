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

    private Double valorVenda;

    @JsonFormat(pattern ="dd/MM/yyyy")
    private LocalDate dataVenda;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Vendas() {
        super();
    }

    public Vendas(Integer id, Double valorVenda, LocalDate dataVenda, Vendedor vendedor, Cliente cliente) {
        this.id = id;
        this.valorVenda = valorVenda;
        this.dataVenda = dataVenda;
        this.vendedor = vendedor;
        this.cliente = cliente;
    }
}
