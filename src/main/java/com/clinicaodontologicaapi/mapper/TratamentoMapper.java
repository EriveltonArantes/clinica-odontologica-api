package com.clinicaodontologicaapi.mapper;

import com.clinicaodontologicaapi.dto.TratamentoRequestDTO;
import com.clinicaodontologicaapi.dto.TratamentoResponseDTO;
import com.clinicaodontologicaapi.model.Tratamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TratamentoMapper {

    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "dentista", ignore = true)
    Tratamento toEntity(TratamentoRequestDTO dto);

    @Mapping(target = "pacienteId", source = "paciente.id")
    @Mapping(target = "dentistaId", source = "dentista.id")
    TratamentoResponseDTO toResponseDTO(Tratamento entity);
}
