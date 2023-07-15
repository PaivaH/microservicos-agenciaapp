package br.com.agenciaapp.agendamento.service;

import java.util.Optional;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.agenciaapp.agendamento.Dto.ConsultaDto;
import br.com.agenciaapp.agendamento.Dto.DetalhesConsultaDto;
import br.com.agenciaapp.agendamento.controller.ConsultaController;
import br.com.agenciaapp.agendamento.http.AgendaClient;
import br.com.agenciaapp.agendamento.model.Consulta;
import br.com.agenciaapp.agendamento.repository.ConsultaRepository;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AgendaClient agenda;

    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(ConsultaController.class);

    private DetalhesConsultaDto detalhes(Long consultaId) {
        DetalhesConsultaDto detalhesConsultaDto = null;
        ResponseEntity<String> response = agenda.detalhesConsulta(consultaId);
        
        try {
            detalhesConsultaDto = objectMapper.readValue(response.getBody(), DetalhesConsultaDto.class);
            
        } catch (Exception e) {
            logger.trace(e.getMessage());
        }

		return detalhesConsultaDto;

    }

    public Consulta criar(Consulta consulta) throws Exception {
        ResponseEntity response = agenda.marcarConsulta(consulta.getAgendaId());

        if(response.getStatusCode() != HttpStatus.NO_CONTENT) {
            throw new Exception("Não foi possivel marcar consulta");
        }

        if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new Exception("Consulta não encontrada");
        }

        return consultaRepository.save(consulta);
    }

    @Transient
    public void deletar(Long id) throws Exception {
        Optional<Consulta> consulta = consultaRepository.findByAgendaId(id);

        ResponseEntity response = agenda.cancelarConsulta(consulta.get().getAgendaId());

        if(response.getStatusCode() != HttpStatus.NO_CONTENT) {
            throw new Exception("Não foi possivel desmarcar consulta");
        }

        consultaRepository.delete(consulta.get());
        System.out.println("FOi");
    }

    public ConsultaDto obterById(Long id) {
        Consulta consulta = consultaRepository.findById(id).get();
        DetalhesConsultaDto detalhesConsultaDto = detalhes(consulta.getAgendaId());

        ConsultaDto consultaDto = new ConsultaDto();
        consultaDto.setId(consulta.getId());
        consultaDto.setPacienteId(consulta.getPaciente().getId());
        consultaDto.setDetalhesConsultaDto(detalhesConsultaDto);
        
        return consultaDto;
    }

    public Page<Consulta> obterByPaciente(Long id, Pageable pageable) {
        return consultaRepository.findByPacienteId(id, pageable);
    }

    public Page<Consulta> obter(Pageable pageable) {
        return consultaRepository.findAll(pageable);

    }

    public Optional<Consulta> agendaExiste(Long id) {
        return consultaRepository.findByAgendaId(id);
    }
}
