package br.com.xbrain.teste.services;

import br.com.xbrain.teste.domain.Vendedor;
import br.com.xbrain.teste.domain.dto.VendedorDTO;
import br.com.xbrain.teste.repositories.VendedorRepository;
import br.com.xbrain.teste.services.exceptions.ObjectnotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class VendedorServiceTest extends VendedorService {

    public static final Integer ID = 1;
    private static final String NOME = "Fernando Vendedor";

    @InjectMocks
    private VendedorServiceTest service;

    @Mock
    private VendedorRepository repository;

    private Optional<Vendedor> optionalVendedor;
    private List<Vendedor> vendedorList;
    private Vendedor vendedor;
    private VendedorDTO vendedorDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startVendedor();
    }

    @Test
    void procuraVendedorPorId() {
        when(repository.findById(anyInt())).thenReturn(optionalVendedor);

        Vendedor response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Vendedor.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
    }

    @Test
    void procuraVendedorPorIdVendedorNaoEncontrado() {
        when(repository.findById(anyInt())).thenThrow(new ObjectnotFoundException("Vendedor Não Encontrado"));

        try{
            service.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectnotFoundException.class, ex.getClass());
            assertEquals("Vendedor Não Encontrado", ex.getMessage());
        }
    }

    @Test
    void procuraTodosVendedores() {
        when(repository.findAll()).thenReturn(List.of(vendedor));

        List<Vendedor> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Vendedor.class, response.get(0).getClass());

        assertEquals(ID, response.get(0).getId());
        assertEquals(NOME, response.get(0).getNome());
    }

    @Test
    void criaVendedor() {
    }

    @Test
    void filtraVendasPorPeriodo() {
    }

    private void startVendedor() {
        vendedor = new Vendedor(ID, NOME);
        vendedorList = List.of(new Vendedor(ID, NOME));
        optionalVendedor = Optional.of(new Vendedor(ID, NOME));
    }
}