package br.com.agenciaapp.cadastroclinica.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agenciaapp.cadastroclinica.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{

    Page<Agenda> findByProfissionalId(Long id, Pageable pageable);

    Page<Agenda> findByDisponivel(Boolean disponivel, Pageable pageable);
}
