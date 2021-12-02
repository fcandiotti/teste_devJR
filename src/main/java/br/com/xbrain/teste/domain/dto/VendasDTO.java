package br.com.xbrain.teste.domain.dto;

import br.com.xbrain.teste.domain.Vendas;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class VendasDTO implements Serializable {

    private Integer id;

    @NotNull(message = "Campo Data da Venda é Obrigatorio!")
    @JsonFormat(pattern ="dd/MM/yyyy")
    private LocalDate dataVenda;

    @NotNull(message = "Campo Valor da Venda é Obrigatorio!")
    private Double valorVenda;

    @NotNull(message = "Campo ID do Vendedor é Obrigatorio!")
    private Integer vendedor;

    @NotNull(message = "Campo ID do cliente é Obrigatorio!")
    private Integer cliente;

    private String nomeVendedor;
    private String nomeCliente;

    public VendasDTO() {
        super();
    }

    public VendasDTO(Vendas obj) {
        this.id = obj.getId();
        this.dataVenda = obj.getDataVenda();
        this.valorVenda = obj.getValorVenda();
        this.nomeVendedor = obj.getVendedor().getNome();
        this.nomeCliente = obj.getCliente().getNome();
        this.vendedor = obj.getVendedor().getId();
        this.cliente = obj.getCliente().getId();
    }
}
