package br.com.agenciaapp.cadastroclinica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalDto {
    private Long id;
    private String nome;
    private String profissao;
    private String especialidades;
    private String conselhoProfissional;
    private ClinicaDto clinica;
}
