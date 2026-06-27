package com.clinicaodontologicaapi.repository;

import com.clinicaodontologicaapi.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
