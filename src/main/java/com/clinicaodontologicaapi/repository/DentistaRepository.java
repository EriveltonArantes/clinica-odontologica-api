package com.clinicaodontologicaapi.repository;

import com.clinicaodontologicaapi.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {
}
