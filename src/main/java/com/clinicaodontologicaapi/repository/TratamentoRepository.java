package com.clinicaodontologicaapi.repository;

import com.clinicaodontologicaapi.model.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {
}
