package br.com.agenciaapp.agendamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.agenciaapp.agendamento.http.AgendaClient;
import br.com.agenciaapp.agendamento.model.Consulta;
import br.com.agenciaapp.agendamento.repository.ConsultaRepository;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AgendaClient agenda;

    public Consulta criar(Consulta consulta) {
        agenda.marcarConsulta(consulta.getAgendaId());
        return consultaRepository.save(consulta);
    }

    public void deletar(Long id) {
        Optional<Consulta> consulta = consultaRepository.findByAgendaId(id);
        agenda.cancelarConsulta(id);
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
