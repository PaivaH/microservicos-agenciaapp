package br.com.agenciaapp.cadastroclinica.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {
    private Long id;

    private Profissional profissional;

    private LocalDateTime diaHora;
    private Boolean disponivel;

}
