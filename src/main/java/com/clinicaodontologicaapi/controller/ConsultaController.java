package com.clinicaodontologicaapi.controller;

import com.clinicaodontologicaapi.dto.ConsultaRequestDTO;
import com.clinicaodontologicaapi.dto.ConsultaResponseDTO;
import com.clinicaodontologicaapi.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Consulta", description = "Gerenciamento de consultas")
@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @Operation(summary = "Listar todos os Consulta")
    @GetMapping
    public List<ConsultaResponseDTO> listar(@RequestParam(required = false) String procedimento, @RequestParam(required = false) Long dentistaId, @RequestParam(required = false) Long pacienteId) {
        List<ConsultaResponseDTO> resultado = service.listar();
        if (procedimento != null && !procedimento.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getProcedimento() != null &&
                item.getProcedimento().toLowerCase().contains(procedimento.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (dentistaId != null) {
            resultado = resultado.stream().filter(item -> dentistaId.equals(item.getDentistaId())).collect(java.util.stream.Collectors.toList());
        }
        if (pacienteId != null) {
            resultado = resultado.stream().filter(item -> pacienteId.equals(item.getPacienteId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Consulta por ID")
    @GetMapping("/{id}")
    public ConsultaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Consulta")
    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> criar(@Valid @RequestBody ConsultaRequestDTO consulta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(consulta));
    }

    @Operation(summary = "Atualizar Consulta")
    @PutMapping("/{id}")
    public ConsultaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ConsultaRequestDTO consulta) {
        return service.atualizar(id, consulta);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Consulta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
