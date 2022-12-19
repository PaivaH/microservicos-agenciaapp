package br.com.agenciaapp.agendamento.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("clinica-ms")
public interface AgendaClient {
    
    @RequestMapping(method = RequestMethod.PUT, value = "/agenda/{id}/marcar")
    void marcarConsulta(@PathVariable long id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/agenda/{id}/cancelar")
    void cancelarConsulta(@PathVariable long id);
}
