package br.com.xbrain.teste.controller;

import br.com.xbrain.teste.domain.dto.VendasDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:vendedores.sql", "classpath:clientes.sql", "classpath:vendas.sql"})
@Transactional
public class VendasControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void salvaVenda() throws Exception {
        VendasDTO vendas = new VendasDTO();
        vendas.setValorVenda(120.55);
        vendas.setVendedor(1);
        vendas.setCliente(1);
        vendas.setDataVenda(LocalDate.of(2022, 1, 11));
        mockMvc.perform(MockMvcRequestBuilders.post("/vendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vendas)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void buscaTodasAsVendas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vendas")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void buscaVendaPorID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vendas/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.valorVenda", Matchers.is(110.00)));
    }

    @Test
    public void deletarVenda() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/vendas/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void alteravenda() throws Exception {
        VendasDTO venda = new VendasDTO();
        venda.setVendedor(1);
        venda.setCliente(2);
        venda.setId(1);
        venda.setValorVenda(35.00);
        venda.setDataVenda(LocalDate.of(2021, 1, 12));
        mockMvc.perform(MockMvcRequestBuilders.put("/vendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(venda)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
