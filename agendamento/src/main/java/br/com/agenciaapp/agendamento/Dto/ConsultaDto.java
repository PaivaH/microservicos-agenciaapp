package br.com.agenciaapp.agendamento.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaDto {
    private Long id;

    private Long pacienteId;

    private DetalhesConsultaDto detalhesConsultaDto;
    
}
