package br.com.agenciaapp.cadastroclinica.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agenciaapp.cadastroclinica.model.Agenda;
import br.com.agenciaapp.cadastroclinica.model.Profissional;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{
    
    Page<Agenda> findByProfissional(Profissional profissional, Pageable pageable);
}
