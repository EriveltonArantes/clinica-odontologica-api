package com.clinicaodontologicaapi.controller;

import com.clinicaodontologicaapi.model.Dentista;
import com.clinicaodontologicaapi.model.Paciente;
import com.clinicaodontologicaapi.model.Consulta;
import com.clinicaodontologicaapi.model.Tratamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Tag(name = "Dashboard", description = "KPIs e totais do sistema")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private com.clinicaodontologicaapi.repository.DentistaRepository dentistaRepository;

    @Autowired
    private com.clinicaodontologicaapi.repository.PacienteRepository pacienteRepository;

    @Autowired
    private com.clinicaodontologicaapi.repository.ConsultaRepository consultaRepository;

    @Autowired
    private com.clinicaodontologicaapi.repository.TratamentoRepository tratamentoRepository;

    @Operation(summary = "Resumo com totais, somas e gráficos de status")
    @Transactional(readOnly = true)
    @GetMapping("/resumo")
    public Map<String, Object> resumo() {
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalDentista", dentistaRepository.count());
        resumo.put("totalPaciente", pacienteRepository.count());
        resumo.put("totalConsulta", consultaRepository.count());
        resumo.put("somaValorConsulta", consultaRepository.findAll().stream().map(e -> e.getValor()).filter(v -> v != null).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add));
        resumo.put("graficoConsulta", consultaRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        resumo.put("totalTratamento", tratamentoRepository.count());
        resumo.put("somaValorTotalTratamento", tratamentoRepository.findAll().stream().filter(e -> e.getValorTotal() != null).mapToDouble(e -> e.getValorTotal()).sum());
        resumo.put("graficoTratamento", tratamentoRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        return resumo;
    }
}
