package br.com.xbrain.teste.config;

import br.com.xbrain.teste.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TesteConfig {

    @Autowired
    public DBService dbService;

    @Bean
    public void instanciaDB() {
        this.dbService.instanciaDB();
    }


}
