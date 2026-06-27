package com.clinicaodontologicaapi.controller;

import com.clinicaodontologicaapi.dto.DentistaRequestDTO;
import com.clinicaodontologicaapi.dto.DentistaResponseDTO;
import com.clinicaodontologicaapi.service.DentistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Dentista", description = "Gerenciamento de dentistas")
@RestController
@RequestMapping("/api/dentistas")
public class DentistaController {

    @Autowired
    private DentistaService service;

    @Operation(summary = "Listar todos os Dentista")
    @GetMapping
    public List<DentistaResponseDTO> listar(@RequestParam(required = false) String nome) {
        List<DentistaResponseDTO> resultado = service.listar();
        if (nome != null && !nome.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getNome() != null &&
                item.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Dentista por ID")
    @GetMapping("/{id}")
    public DentistaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Dentista")
    @PostMapping
    public ResponseEntity<DentistaResponseDTO> criar(@Valid @RequestBody DentistaRequestDTO dentista) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dentista));
    }

    @Operation(summary = "Atualizar Dentista")
    @PutMapping("/{id}")
    public DentistaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody DentistaRequestDTO dentista) {
        return service.atualizar(id, dentista);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Dentista")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
