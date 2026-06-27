package com.clinicaodontologicaapi.mapper;

import com.clinicaodontologicaapi.dto.ConsultaRequestDTO;
import com.clinicaodontologicaapi.dto.ConsultaResponseDTO;
import com.clinicaodontologicaapi.model.Consulta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {

    @Mapping(target = "dentista", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    Consulta toEntity(ConsultaRequestDTO dto);

    @Mapping(target = "dentistaId", source = "dentista.id")
    @Mapping(target = "pacienteId", source = "paciente.id")
    ConsultaResponseDTO toResponseDTO(Consulta entity);
}
