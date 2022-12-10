package br.com.agenciaapp.cadastroclinica.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfissionaisDto {
    private Long id;
    private String nome;
    private String profissao;
    private String especialidades;
    private String conselhoProfissional;
}
