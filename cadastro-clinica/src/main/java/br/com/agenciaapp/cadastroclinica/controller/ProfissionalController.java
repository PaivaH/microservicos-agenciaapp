package br.com.agenciaapp.cadastroclinica.controller;

import java.net.URI;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.agenciaapp.cadastroclinica.dto.ProfissionalDto;
import br.com.agenciaapp.cadastroclinica.service.ProfissionalService;

@RestController
@RequestMapping("profissional")
public class ProfissionalController {
    
    @Autowired
    private ProfissionalService profissionalService;

    @GetMapping
    public Page<ProfissionalDto> listar (Pageable pageable) {
        return profissionalService.obter(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalDto> detalhar(@PathVariable @NotNull Long id) {
        ProfissionalDto ProfissionalDto = profissionalService.obterById(id);

        return ResponseEntity.ok(ProfissionalDto);
    }

    @PostMapping
    public ResponseEntity<ProfissionalDto> cadastrar(@RequestBody @Valid ProfissionalDto dto, UriComponentsBuilder uriBuilder) {
        ProfissionalDto ProfissionalDto = profissionalService.criarProfissional(dto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(ProfissionalDto.getId()).toUri();

        return ResponseEntity.created(endereco).body(ProfissionalDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid ProfissionalDto dto) {
        ProfissionalDto atualizado = profissionalService.atualizarProfissional(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProfissionalDto> remover(@PathVariable @NotNull Long id) {
        profissionalService.excluirProfissional(id);
        return ResponseEntity.noContent().build();
    }
}
