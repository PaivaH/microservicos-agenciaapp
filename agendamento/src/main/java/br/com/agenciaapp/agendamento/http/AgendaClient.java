package br.com.agenciaapp.agendamento.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("clinica-ms")
public interface AgendaClient {

    @GetMapping("/agenda/{id}")
    ResponseEntity detalhesConsulta(@PathVariable long id);
    
    @PutMapping("/agenda/{id}/marcar")
    ResponseEntity marcarConsulta(@PathVariable long id);

    @PutMapping("/agenda/{id}/cancelar")
    ResponseEntity cancelarConsulta(@PathVariable long id);
}
