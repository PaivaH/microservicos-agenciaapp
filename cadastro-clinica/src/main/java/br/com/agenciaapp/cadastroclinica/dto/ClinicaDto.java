package br.com.agenciaapp.cadastroclinica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicaDto {
    private Long id;
    private String nome;
    private String endereco;
    private String responsavel;
}
