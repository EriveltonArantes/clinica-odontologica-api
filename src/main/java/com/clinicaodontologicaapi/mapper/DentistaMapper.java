package com.clinicaodontologicaapi.mapper;

import com.clinicaodontologicaapi.dto.DentistaRequestDTO;
import com.clinicaodontologicaapi.dto.DentistaResponseDTO;
import com.clinicaodontologicaapi.model.Dentista;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DentistaMapper {

    Dentista toEntity(DentistaRequestDTO dto);

    DentistaResponseDTO toResponseDTO(Dentista entity);
}
