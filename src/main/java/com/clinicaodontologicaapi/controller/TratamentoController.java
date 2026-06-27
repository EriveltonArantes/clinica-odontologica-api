package com.clinicaodontologicaapi.controller;

import com.clinicaodontologicaapi.dto.TratamentoRequestDTO;
import com.clinicaodontologicaapi.dto.TratamentoResponseDTO;
import com.clinicaodontologicaapi.service.TratamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Tratamento", description = "Gerenciamento de tratamentos")
@RestController
@RequestMapping("/api/tratamentos")
public class TratamentoController {

    @Autowired
    private TratamentoService service;

    @Operation(summary = "Listar todos os Tratamento")
    @GetMapping
    public List<TratamentoResponseDTO> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) Long pacienteId, @RequestParam(required = false) Long dentistaId) {
        List<TratamentoResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (pacienteId != null) {
            resultado = resultado.stream().filter(item -> pacienteId.equals(item.getPacienteId())).collect(java.util.stream.Collectors.toList());
        }
        if (dentistaId != null) {
            resultado = resultado.stream().filter(item -> dentistaId.equals(item.getDentistaId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Tratamento por ID")
    @GetMapping("/{id}")
    public TratamentoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Tratamento")
    @PostMapping
    public ResponseEntity<TratamentoResponseDTO> criar(@Valid @RequestBody TratamentoRequestDTO tratamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(tratamento));
    }

    @Operation(summary = "Atualizar Tratamento")
    @PutMapping("/{id}")
    public TratamentoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody TratamentoRequestDTO tratamento) {
        return service.atualizar(id, tratamento);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Tratamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
