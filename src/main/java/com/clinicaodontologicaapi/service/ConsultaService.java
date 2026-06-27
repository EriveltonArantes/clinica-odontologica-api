package com.clinicaodontologicaapi.service;

import com.clinicaodontologicaapi.dto.ConsultaRequestDTO;
import com.clinicaodontologicaapi.dto.ConsultaResponseDTO;
import com.clinicaodontologicaapi.exception.ResourceNotFoundException;
import com.clinicaodontologicaapi.mapper.ConsultaMapper;
import com.clinicaodontologicaapi.model.Consulta;
import com.clinicaodontologicaapi.repository.ConsultaRepository;
import com.clinicaodontologicaapi.repository.DentistaRepository;
import com.clinicaodontologicaapi.model.Dentista;
import com.clinicaodontologicaapi.repository.PacienteRepository;
import com.clinicaodontologicaapi.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private ConsultaMapper mapper;

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConsultaResponseDTO buscar(Long id) {
        Consulta entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public ConsultaResponseDTO criar(ConsultaRequestDTO dto) {
        Consulta entity = mapper.toEntity(dto);
        Dentista dentista = dentistaRepository.findById(dto.getDentistaId())
            .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com id: " + dto.getDentistaId()));
        entity.setDentista(dentista);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + dto.getPacienteId()));
        entity.setPaciente(paciente);
        Consulta salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta não encontrado com id: " + id);
        }
        Consulta entity = mapper.toEntity(dto);
        entity.setId(id);
        Dentista dentista = dentistaRepository.findById(dto.getDentistaId())
            .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com id: " + dto.getDentistaId()));
        entity.setDentista(dentista);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + dto.getPacienteId()));
        entity.setPaciente(paciente);
        Consulta salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
