package br.com.agenciaapp.cadastroclinica.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.agenciaapp.cadastroclinica.dto.ClinicaDto;
import br.com.agenciaapp.cadastroclinica.model.Clinica;
import br.com.agenciaapp.cadastroclinica.repository.ClinicaRepository;

@Service
public class ClinicaService {

    @Autowired
    private ClinicaRepository clinicaRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public Page<ClinicaDto> obter(Pageable pageable) {
        return clinicaRepository
                .findAll(pageable)
                .map(c -> modelMapper.map(c, ClinicaDto.class));
    }

    public Clinica obterById(Long id) {
        return clinicaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public Clinica criarClinica(Clinica clinica) {
        return clinicaRepository.save(clinica);
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
