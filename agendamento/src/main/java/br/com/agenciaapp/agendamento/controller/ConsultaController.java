package br.com.agenciaapp.agendamento.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenciaapp.agendamento.model.Consulta;
import br.com.agenciaapp.agendamento.service.ConsultaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @PostMapping()
    @CircuitBreaker(name = "marcarConsulta")
    public ResponseEntity<Consulta> cadastrar(@RequestBody @Valid Consulta Consulta) {
        Consulta dto = consultaService.criar(Consulta);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    @CircuitBreaker(name = "cancelarConsulta")
    public ResponseEntity<Consulta> deletar(@PathVariable @NotNull Long id) {
        consultaService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Consulta> obterById(@PathVariable Long id) {
        Consulta dto = consultaService.obterById(id).get();

        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}/paciente")
    public Page<Consulta> obterByPaciente(@PathVariable Long id, Pageable pageable) {
        return consultaService.obterByPaciente(id, pageable);
    }

    @GetMapping()
    public Page<Consulta> obter(Pageable pageable) {
        return consultaService.obter(pageable);
    }

}
