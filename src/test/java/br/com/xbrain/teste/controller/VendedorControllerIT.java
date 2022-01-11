package br.com.xbrain.teste.controller;

import br.com.xbrain.teste.domain.dto.VendedorDTO;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:vendedores.sql", "classpath:clientes.sql", "classpath:vendas.sql"})
@Transactional
public class VendedorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void retornaTodosOsVendedores() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vendedores")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void deveBuscarVendedorPorId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vendedores/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Fernando")));
    }

    @Test
    public void deveSalvarVendedor() throws Exception {
        VendedorDTO vendedor = new VendedorDTO();
        vendedor.setNome("Fernando");
        mockMvc.perform(MockMvcRequestBuilders.post("/vendedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vendedor)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void devefiltraVendasPorPeriodo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vendedores/filtro?dataInicio=09/01/2022&dataFim=12/01/2022"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", Matchers.is("Fernando Vendedor TOP 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalVendas", Matchers.is(220.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantidadeVenda", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].informacoesDasVendas[0]", Matchers.is("10/01/2022 Valor Venda: 110.0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mediaVendas", Matchers.is(110.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mediaVendasPorDia", Matchers.is(0.5)));
    }


}
