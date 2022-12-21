package br.com.agenciaapp.cadastroclinica.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("agendamento-ms")
public interface AgendamentoClient {
    @RequestMapping(method = RequestMethod.DELETE, value = "/consulta/{id}")
    void cancelarConsulta(@PathVariable long id);

    @RequestMapping(method=RequestMethod.GET, value="/consulta/{id}/agendada")
    public Boolean checkarAgendamento(@RequestParam long id);
    
}
