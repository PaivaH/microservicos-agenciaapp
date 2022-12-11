package br.com.agenciaapp.cadastroclinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agenciaapp.cadastroclinica.model.Clinica;

public interface ClinicaRepository extends JpaRepository<Clinica, Long>{
    
}
