package br.com.agenciaapp.agendamento.Dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaDto {
    private Long id;

    private Long agendaId;
    
    private LocalDateTime diaHora;
    
}
