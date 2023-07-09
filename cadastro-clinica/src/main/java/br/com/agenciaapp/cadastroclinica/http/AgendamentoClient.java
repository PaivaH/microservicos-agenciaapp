package br.com.agenciaapp.cadastroclinica.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("agendamento-ms")
public interface AgendamentoClient {
    @DeleteMapping("/consulta/{id}")
    void cancelarConsulta(@PathVariable long id);

    @GetMapping("/consulta/{id}/agendada")
    public Boolean checkarAgendamento(@RequestParam long id);
    
}
