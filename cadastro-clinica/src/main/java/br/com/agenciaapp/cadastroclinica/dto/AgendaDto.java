package br.com.agenciaapp.cadastroclinica.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaDto {
    private Long id;

    private LocalDateTime diaHora;

    private Boolean disponivel;

    private ProfissionalDto profissional;
}
