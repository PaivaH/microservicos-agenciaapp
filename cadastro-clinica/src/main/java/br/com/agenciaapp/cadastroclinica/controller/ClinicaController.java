package br.com.agenciaapp.cadastroclinica.controller;

import java.net.URI;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.agenciaapp.cadastroclinica.dto.ClinicaDto;
import br.com.agenciaapp.cadastroclinica.model.Clinica;
import br.com.agenciaapp.cadastroclinica.service.ClinicaService;

@RestController()
@RequestMapping("/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaService clinicaService;

    private Logger logger = LoggerFactory.getLogger(ClinicaController.class);

    @GetMapping
    public Page<ClinicaDto> listar (Pageable pageable) {
        logger.info("listar ClinicaController");
        return clinicaService.obter(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clinica> detalhar(@PathVariable @NotNull Long id) {
        logger.info("detalhar ClinicaController");

        try{
            Clinica clinica = clinicaService.obterById(id);
            return ResponseEntity.ok(clinica);
        } catch (Exception e) {
            logger.error("Não encontrado" ,e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<Clinica> cadastrar(@RequestBody @Valid Clinica dto, UriComponentsBuilder uriBuilder) {
        logger.info("cadastrar ClinicaController");
        Clinica clinica = clinicaService.criarClinica(dto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(clinica.getId()).toUri();

        return ResponseEntity.created(endereco).body(clinica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaDto> atualizar(@PathVariable @NotNull Long id, @RequestBody ClinicaDto dto) {
        logger.info("atualizar ClinicaController");
        ClinicaDto atualizado = clinicaService.atualizarClinica(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClinicaDto> remover(@PathVariable @NotNull Long id) {
        logger.info("remover ClinicaController");
        clinicaService.excluirClinica(id);
        return ResponseEntity.noContent().build();
    }

}
