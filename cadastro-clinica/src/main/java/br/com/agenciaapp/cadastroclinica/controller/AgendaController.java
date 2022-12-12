package br.com.agenciaapp.cadastroclinica.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenciaapp.cadastroclinica.dto.AgendaDto;
import br.com.agenciaapp.cadastroclinica.service.AgendaService;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    @Autowired
    AgendaService agendaService;

    @GetMapping
    public Page<AgendaDto> listar(Pageable pageable, @RequestParam(defaultValue = "true") Boolean disponivel) {
        return agendaService.obter(pageable, disponivel);
    }

    @PostMapping("/{id}/profissional")
    public ResponseEntity<AgendaDto> cadastrar(
            @RequestBody @Valid AgendaDto dto,
            @PathVariable(name = "id") Long id) {
        AgendaDto agendaDto = agendaService.criarAgenda(dto, id);

        return ResponseEntity.ok(agendaDto);
    }

    @GetMapping("/{id}/profissional")
    public Page<AgendaDto> agendaProfissional(@PathVariable Long id, Pageable pageable) {
        return agendaService.agendaProssional(id, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDto> obterAgendaId(@PathVariable(name = "id") Long id) {
        AgendaDto agendaDto = agendaService.obterById(id);

        return ResponseEntity.ok(agendaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AgendaDto> remover(@PathVariable @NotNull Long id) {
        agendaService.excluirAgenda(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/consulta")
    public ResponseEntity<AgendaDto> marcarConsulta(@PathVariable @NotNull Long id) {
        AgendaDto dto = agendaService.consulta(id, false);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}/consulta")
    public ResponseEntity<AgendaDto> cancelarConsulta(@PathVariable @NotNull Long id) {
        AgendaDto dto = agendaService.consulta(id, true);
        return ResponseEntity.ok(dto);
    }

}
