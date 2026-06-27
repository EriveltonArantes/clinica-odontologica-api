package com.clinicaodontologicaapi.service;

import com.clinicaodontologicaapi.dto.TratamentoRequestDTO;
import com.clinicaodontologicaapi.dto.TratamentoResponseDTO;
import com.clinicaodontologicaapi.exception.ResourceNotFoundException;
import com.clinicaodontologicaapi.mapper.TratamentoMapper;
import com.clinicaodontologicaapi.model.Tratamento;
import com.clinicaodontologicaapi.repository.TratamentoRepository;
import com.clinicaodontologicaapi.repository.PacienteRepository;
import com.clinicaodontologicaapi.model.Paciente;
import com.clinicaodontologicaapi.repository.DentistaRepository;
import com.clinicaodontologicaapi.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TratamentoService {

    @Autowired
    private TratamentoRepository repository;

    @Autowired
    private TratamentoMapper mapper;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    @Transactional(readOnly = true)
    public List<TratamentoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TratamentoResponseDTO buscar(Long id) {
        Tratamento entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tratamento não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public TratamentoResponseDTO criar(TratamentoRequestDTO dto) {
        Tratamento entity = mapper.toEntity(dto);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + dto.getPacienteId()));
        entity.setPaciente(paciente);
        Dentista dentista = dentistaRepository.findById(dto.getDentistaId())
            .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com id: " + dto.getDentistaId()));
        entity.setDentista(dentista);
        Tratamento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public TratamentoResponseDTO atualizar(Long id, TratamentoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tratamento não encontrado com id: " + id);
        }
        Tratamento entity = mapper.toEntity(dto);
        entity.setId(id);
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + dto.getPacienteId()));
        entity.setPaciente(paciente);
        Dentista dentista = dentistaRepository.findById(dto.getDentistaId())
            .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com id: " + dto.getDentistaId()));
        entity.setDentista(dentista);
        Tratamento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tratamento não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
