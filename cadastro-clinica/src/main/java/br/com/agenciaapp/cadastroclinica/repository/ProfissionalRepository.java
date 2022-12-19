package br.com.agenciaapp.cadastroclinica.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agenciaapp.cadastroclinica.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>{

    Page<Profissional> findByClinicaId(Pageable page,Long id);
}
