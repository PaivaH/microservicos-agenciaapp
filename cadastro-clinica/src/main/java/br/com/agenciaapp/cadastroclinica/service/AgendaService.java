package br.com.agenciaapp.cadastroclinica.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.agenciaapp.cadastroclinica.dto.AgendaDto;
import br.com.agenciaapp.cadastroclinica.http.AgendamentoClient;
import br.com.agenciaapp.cadastroclinica.model.Agenda;
import br.com.agenciaapp.cadastroclinica.model.Profissional;
import br.com.agenciaapp.cadastroclinica.repository.AgendaRepository;
import br.com.agenciaapp.cadastroclinica.repository.ProfissionalRepository;

@Service
public class AgendaService {
    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    ProfissionalRepository profissionalService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AgendamentoClient agendamentoClient;

    public Page<AgendaDto> obter(Pageable pageable, Boolean disponivel) {
        if (disponivel) {
            return agendaRepository
                    .findByDisponivel(disponivel, pageable)
                    .map(c -> modelMapper.map(c, AgendaDto.class));
        }
        return agendaRepository
                .findAll(pageable)
                .map(c -> modelMapper.map(c, AgendaDto.class));
    }

    public AgendaDto criarAgenda(AgendaDto dto, Long id) {
        Agenda agenda = modelMapper.map(dto, Agenda.class);
        Profissional profissional = profissionalService.findById(id).get();
        agenda.setProfissional(profissional);
        agendaRepository.save(agenda);

        return modelMapper.map(agenda, AgendaDto.class);
    }

    public Page<AgendaDto> agendaProssional(Long id, Pageable pageable) {

        return agendaRepository
                .findByProfissionalId(id, pageable)
                .map(c -> modelMapper.map(c, AgendaDto.class));

    }

    public AgendaDto obterById(Long id) {
        Agenda agenda = agendaRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(agenda, AgendaDto.class);
    }

    @Transactional
    public void excluirAgenda(Long id) {
        Boolean agenda = agendamentoClient.checkarAgendamento(id);
        System.out.println(agenda);
        if (agenda == true) {
            try {
                agendamentoClient.cancelarConsulta(id);
            } catch (Exception exception) {
                exception.getMessage();
            }
        }
        agendaRepository.deleteById(id);

    }

    public AgendaDto consulta(Long id, Boolean marcar) throws Exception {
        Optional<Agenda> agenda = agendaRepository.findById(id);
        if(!marcar && !agenda.get().getDisponivel()) {
            throw new Exception("Consulta não disponivel");
        }

        if(marcar && agenda.get().getDisponivel()) {
            throw new Exception("Não foi possivel fazer a operação");
        }
        agenda.get().setDisponivel(marcar);
        Agenda dto = agendaRepository.save(agenda.get());
        return modelMapper.map(dto, AgendaDto.class);
    }
}
