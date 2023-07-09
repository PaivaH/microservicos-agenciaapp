package br.com.agenciaapp.agendamento.service;

import java.util.Optional;

import javax.persistence.Transient;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.agenciaapp.agendamento.Dto.detalhesConsultaDto;
import br.com.agenciaapp.agendamento.http.AgendaClient;
import br.com.agenciaapp.agendamento.model.Consulta;
import br.com.agenciaapp.agendamento.repository.ConsultaRepository;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AgendaClient agenda;

    public detalhesConsultaDto detalhes(Long consultaId) {
        // @TODO: Consultar datas no MS de clinicas
        ResponseEntity response = agenda.detalhesConsulta(consultaId);
        return null;
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
        Optional<Consulta> consulta = consultaRepository.findById(id);

        ResponseEntity response = agenda.cancelarConsulta(consulta.get().getAgendaId());

        if(response.getStatusCode() != HttpStatus.NO_CONTENT) {
            throw new Exception("Não foi possivel desmarcar consulta");
        }

        consultaRepository.delete(consulta.get());
        System.out.println("FOi");
    }

    public Optional<Consulta> obterById(Long id) {
        return consultaRepository.findById(id);
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
