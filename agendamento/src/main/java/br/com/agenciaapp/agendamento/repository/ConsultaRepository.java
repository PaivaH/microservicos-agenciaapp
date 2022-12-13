package br.com.agenciaapp.agendamento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agenciaapp.agendamento.model.Consulta;
import br.com.agenciaapp.agendamento.model.Paciente;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

    Page<Consulta> findByPaciente(Paciente paciente, Pageable pageable);

    Page<Consulta> findByPacienteId(Long paciente, Pageable pageable);
    
}