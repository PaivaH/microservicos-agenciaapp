package br.com.agenciaapp.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agenciaapp.agendamento.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
}
