package br.com.agenciaapp.cadastroclinica.controller;

import java.net.URI;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.agenciaapp.cadastroclinica.dto.ProfissionaisDto;
import br.com.agenciaapp.cadastroclinica.dto.ProfissionalDto;
import br.com.agenciaapp.cadastroclinica.service.ProfissionalService;

@RestController
@RequestMapping("profissional")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    private Logger logger = LoggerFactory.getLogger(ProfissionalController.class);

    @GetMapping
    public Page<ProfissionaisDto> listar(Pageable pageable,
            @RequestParam(name = "clinica", required = false, defaultValue = "0") Long id) {

        logger.info("listar ProfissionalController");
        
        return profissionalService.obter(pageable, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalDto> detalhar(@PathVariable @NotNull Long id) {
        logger.info("detalhar ProfissionalController");

        ProfissionalDto ProfissionalDto = profissionalService.obterById(id);

        return ResponseEntity.ok(ProfissionalDto);
    }

    @PostMapping
    public ResponseEntity<ProfissionalDto> cadastrar(@RequestBody @Valid ProfissionalDto dto,
            UriComponentsBuilder uriBuilder) {
        logger.info("cadastrar ProfissionalController");

        ProfissionalDto ProfissionalDto = profissionalService.criarProfissional(dto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(ProfissionalDto.getId()).toUri();

        return ResponseEntity.created(endereco).body(ProfissionalDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalDto> atualizar(@PathVariable @NotNull Long id,
            @RequestBody @Valid ProfissionalDto dto) {

        logger.info("atualizar ProfissionalController");
        ProfissionalDto atualizado = profissionalService.atualizarProfissional(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfissionalDto> remover(@PathVariable @NotNull Long id) {
        logger.info("remover ProfissionalController");

        profissionalService.excluirProfissional(id);
        return ResponseEntity.noContent().build();
    }
}
