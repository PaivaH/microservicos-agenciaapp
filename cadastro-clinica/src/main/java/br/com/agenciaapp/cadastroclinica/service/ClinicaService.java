package br.com.agenciaapp.cadastroclinica.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.agenciaapp.cadastroclinica.dto.ClinicaDto;
import br.com.agenciaapp.cadastroclinica.dto.ProfissionaisDto;
import br.com.agenciaapp.cadastroclinica.model.Clinica;
import br.com.agenciaapp.cadastroclinica.model.Profissional;
import br.com.agenciaapp.cadastroclinica.repository.ClinicaRepository;
import br.com.agenciaapp.cadastroclinica.repository.ProfissionalRepository;

@Service
public class ClinicaService {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ClinicaDto> obter(Pageable pageable) {
        return clinicaRepository
                .findAll(pageable)
                .map(c -> modelMapper.map(c, ClinicaDto.class));
    }

    public ClinicaDto obterById(Long id) {
        Clinica clinica = clinicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(clinica, ClinicaDto.class);
    }

    public List<ProfissionaisDto> obterProfissionais(Long id) {
        Optional<Clinica> clinica = clinicaRepository.findById(id);
        List<Profissional> temp = profissionalRepository
                .findByClinica(clinica.get());
        return modelMapper.map(temp, new TypeToken<List<ProfissionaisDto>>() {
        }.getType());
    }

    public ClinicaDto criarClinica(ClinicaDto dto) {
        Clinica clinica = modelMapper.map(dto, Clinica.class);
        clinicaRepository.save(clinica);

        return modelMapper.map(clinica, ClinicaDto.class);
    }

    public ClinicaDto atualizarClinica(Long id, ClinicaDto dto) {
        Clinica clinica = modelMapper.map(dto, Clinica.class);
        clinica.setId(id);
        clinica = clinicaRepository.save(clinica);
        return modelMapper.map(clinica, ClinicaDto.class);
    }

    public void excluirClinica(Long id) {
        clinicaRepository.deleteById(id);
    }
}
