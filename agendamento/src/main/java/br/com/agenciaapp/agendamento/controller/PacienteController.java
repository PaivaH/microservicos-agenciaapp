package br.com.agenciaapp.agendamento.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenciaapp.agendamento.model.Paciente;
import br.com.agenciaapp.agendamento.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    PacienteService pacienteService;

    private Logger logger = LoggerFactory.getLogger(PacienteController.class);

    @PostMapping()
    public ResponseEntity<Paciente> cadastrar(@RequestBody @Valid Paciente paciente) {
        Paciente dto = pacienteService.criar(paciente);

        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Paciente> obterById(@PathVariable Long id) {
        Paciente dto = pacienteService.obterPaciente(id).get();

        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public Page<Paciente> obter(Pageable pageable) {
        logger.info("obter Pacientes");
        return pacienteService.obterPacientes(pageable);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Paciente> atualizar(
            @RequestBody @Valid Paciente paciente,
            @RequestParam @NotNull Long id) {
        Paciente dto = pacienteService.atualizarPaciente(id, paciente);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Paciente> deletar(@PathVariable @NotNull Long id) {
        pacienteService.deletarPaciente(id);

        return ResponseEntity.noContent().build();
    }

}
