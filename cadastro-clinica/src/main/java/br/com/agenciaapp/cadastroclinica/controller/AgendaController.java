package br.com.agenciaapp.cadastroclinica.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import br.com.agenciaapp.cadastroclinica.dto.AgendaDto;
import br.com.agenciaapp.cadastroclinica.service.AgendaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    @Autowired
    private AgendaService agendaService;

    private Logger logger = LoggerFactory.getLogger(AgendaController.class);

    @GetMapping
    public Page<AgendaDto> listar(Pageable pageable, @RequestParam(defaultValue = "true") Boolean disponivel) {
        logger.info("Lista AgendaController");
        return agendaService.obter(pageable, disponivel);
    }

    @PostMapping("/{id}/profissional")
    public ResponseEntity<AgendaDto> cadastrar(
            @RequestBody @Valid AgendaDto dto,
            @PathVariable Long id) {
        logger.info("cadastrar AgendaController");
        AgendaDto agendaDto = agendaService.criarAgenda(dto, id);

        return ResponseEntity.ok(agendaDto);
    }

    @GetMapping("/{id}/profissional")
    public ResponseEntity<Page<AgendaDto>> agendaProfissional(@PathVariable Long id, Pageable pageable) {
        logger.info("agendaProfissional AgendaController");
        try {
            Page<AgendaDto> agendaDto = agendaService.agendaProssional(id, pageable);
            
            return ResponseEntity.ok(agendaDto);
        } catch (Exception e) {
            logger.error("Não encontrado", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaDto> obterAgendaId(@PathVariable Long id) {
        logger.info("obterAgendaId AgendaController");

        try {
            AgendaDto agendaDto = agendaService.obterById(id);

            return ResponseEntity.ok(agendaDto);
        } catch (Exception e) {
            logger.error("Não encontrado", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    @CircuitBreaker(name = "agenda", fallbackMethod = "")
    public ResponseEntity<AgendaDto> remover(@PathVariable @NotNull Long id) {
        logger.info("remover AgendaController");
        agendaService.excluirAgenda(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/marcar")
    public ResponseEntity<Void> marcarConsulta(@PathVariable @NotNull Long id) {
        logger.info("marcarConsulta AgendaController");
        try {
            agendaService.consulta(id, false);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage()); 
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable @NotNull Long id) {
        logger.info("cancelarConsulta AgendaController");
        try {
            agendaService.consulta(id, true);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage()); 
        }
    }

}
