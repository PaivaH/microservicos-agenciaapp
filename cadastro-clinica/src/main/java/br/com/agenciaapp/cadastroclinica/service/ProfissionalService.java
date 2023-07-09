package br.com.agenciaapp.cadastroclinica.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.agenciaapp.cadastroclinica.dto.ProfissionalDto;
import br.com.agenciaapp.cadastroclinica.model.Profissional;
import br.com.agenciaapp.cadastroclinica.repository.ProfissionalRepository;

@Service
public class ProfissionalService {
    
    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ProfissionalDto> obter (Pageable pageable, Long id) {
        if(id > 0){
            return profissionalRepository
                .findByClinicaId(pageable, id)
                .map(c -> modelMapper.map(c, ProfissionalDto.class));
        }
        return profissionalRepository
                .findAll(pageable)
                .map(c -> modelMapper.map(c, ProfissionalDto.class));
    }

    public ProfissionalDto obterById (Long id) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(profissional, ProfissionalDto.class);
    }

    public ProfissionalDto criarProfissional(ProfissionalDto dto) {
        Profissional profissional = modelMapper.map(dto, Profissional.class);
        profissionalRepository.save(profissional);

        return modelMapper.map(profissional, ProfissionalDto.class);
    }

    public ProfissionalDto atualizarProfissional(Long id, ProfissionalDto dto) {
        Profissional profissional = modelMapper.map(dto, Profissional.class);
        profissional.setId(id);
        profissional = profissionalRepository.save(profissional);
        return modelMapper.map(profissional, ProfissionalDto.class);
    }

    public void excluirProfissional(Long id) {
        profissionalRepository.deleteById(id);
    }
}
