package br.com.agenciaapp.agendamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.agenciaapp.agendamento.model.Paciente;
import br.com.agenciaapp.agendamento.repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente criar(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> obterPaciente(Long id) {
        return pacienteRepository.findById(id);
    }

    public Page<Paciente> obterPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

    public Paciente atualizarPaciente(Long id, Paciente paciente){
        paciente.setId(id);
        return pacienteRepository.save(paciente);
    }

    public void deletarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
}
