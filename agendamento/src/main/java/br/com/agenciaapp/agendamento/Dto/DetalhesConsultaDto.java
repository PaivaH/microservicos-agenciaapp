package br.com.agenciaapp.agendamento.Dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalhesConsultaDto {
    @JsonProperty("id")
    private Long agendaId;
    
    @JsonProperty("diaHora")
    private LocalDateTime diaHora;

    @JsonProperty("profissionalId")
    private Long profissionalId;
    
}