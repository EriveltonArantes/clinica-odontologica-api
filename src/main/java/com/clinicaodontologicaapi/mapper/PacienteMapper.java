package com.clinicaodontologicaapi.mapper;

import com.clinicaodontologicaapi.dto.PacienteRequestDTO;
import com.clinicaodontologicaapi.dto.PacienteResponseDTO;
import com.clinicaodontologicaapi.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    Paciente toEntity(PacienteRequestDTO dto);

    PacienteResponseDTO toResponseDTO(Paciente entity);
}
