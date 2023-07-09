package br.com.agenciaapp.agendamento.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("clinica-ms")
public interface AgendaClient {

    @RequestMapping(method = RequestMethod.GET, value = "/agenda/{id}")
    ResponseEntity detalhesConsulta(@PathVariable long id);
    
    @RequestMapping(method = RequestMethod.PUT, value = "/agenda/{id}/marcar")
    ResponseEntity marcarConsulta(@PathVariable long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/agenda/{id}/cancelar")
    ResponseEntity cancelarConsulta(@PathVariable long id);
}
