package br.com.agenciaapp.agendamento.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Convenio {
    private long id;
    
    private String numero;

    private STATUS status;

    private COBERTURA corbertura;

    private TIPO tipo;
}
