package br.com.agenciaapp.agendamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapper {
    
    @Bean
    public ObjectMapper obterModelMapper() {
        return new ObjectMapper();
    }
}
