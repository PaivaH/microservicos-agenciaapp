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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenciaapp.cadastroclinica.dto.AgendaDto;
import br.com.agenciaapp.cadastroclinica.service.AgendaService;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    @Autowired
    AgendaService agendaService;

    @GetMapping
    public Page<AgendaDto> listar(Pageable pageable) {
        return agendaService.obter(pageable);
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

}
