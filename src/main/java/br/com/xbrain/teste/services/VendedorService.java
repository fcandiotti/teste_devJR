package br.com.xbrain.teste.services;

import br.com.xbrain.teste.domain.Vendedor;
import br.com.xbrain.teste.domain.dto.VendedorDTO;
import br.com.xbrain.teste.repositories.VendedorRepository;
import br.com.xbrain.teste.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository repository;

    public Vendedor findById(Integer id) {
        Optional<Vendedor> obj = repository.findById(id);
                return obj.orElseThrow(() -> new ObjectnotFoundException("Vendedor Não Encontrado: "+ id));
    }

    public List<Vendedor> findAll() {
        return repository.findAll();
    }

    public Vendedor create(@Valid VendedorDTO objDTO) {
        objDTO.setId(null);
        Vendedor newOjb = new Vendedor(objDTO);
        return repository.save(newOjb);
    }

    public List<VendedorDTO> filtraVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim){
       List<Vendedor> vendedores = repository.filtraVendasPorPeriodo(dataInicio, dataFim);
       return vendedores.stream()
               .map(vendedor -> new VendedorDTO(vendedor, dataInicio, dataFim))
               .collect(Collectors.toList());
    }

}
