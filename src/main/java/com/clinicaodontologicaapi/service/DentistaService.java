package com.clinicaodontologicaapi.service;

import com.clinicaodontologicaapi.dto.DentistaRequestDTO;
import com.clinicaodontologicaapi.dto.DentistaResponseDTO;
import com.clinicaodontologicaapi.exception.ResourceNotFoundException;
import com.clinicaodontologicaapi.mapper.DentistaMapper;
import com.clinicaodontologicaapi.model.Dentista;
import com.clinicaodontologicaapi.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DentistaService {

    @Autowired
    private DentistaRepository repository;

    @Autowired
    private DentistaMapper mapper;

    @Transactional(readOnly = true)
    public List<DentistaResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DentistaResponseDTO buscar(Long id) {
        Dentista entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public DentistaResponseDTO criar(DentistaRequestDTO dto) {
        Dentista entity = mapper.toEntity(dto);
        Dentista salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public DentistaResponseDTO atualizar(Long id, DentistaRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Dentista não encontrado com id: " + id);
        }
        Dentista entity = mapper.toEntity(dto);
        entity.setId(id);
        Dentista salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Dentista não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
