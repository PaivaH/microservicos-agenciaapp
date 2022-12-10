package br.com.agenciaapp.cadastroclinica.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agenciaapp.cadastroclinica.model.Clinica;
import br.com.agenciaapp.cadastroclinica.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>{
    
    List<Profissional> findByClinica(Clinica clinica);
}
