package com.clinicaodontologicaapi.dto;

import jakarta.validation.constraints.*;

public class ConsultaRequestDTO {

    @NotNull(message = "DentistaId é obrigatório")
    @Positive(message = "DentistaId deve ser um ID válido (positivo)")
    private Long dentistaId;
    @NotNull(message = "PacienteId é obrigatório")
    @Positive(message = "PacienteId deve ser um ID válido (positivo)")
    private Long pacienteId;
    @FutureOrPresent(message = "data hora não pode ser retroativo")
    @NotNull(message = "data hora não pode ser nulo")
    private java.time.LocalDateTime dataHora;
    @NotBlank(message = "status não pode estar em branco")
    private String status;
    @NotBlank(message = "procedimento não pode estar em branco")
    private String procedimento;
    @DecimalMin(value = "0.0", message = "valor não pode ser negativo")
    @NotNull(message = "valor não pode ser nulo")
    private java.math.BigDecimal valor;

    private String observacoes;

    public Long getDentistaId() { return dentistaId; }
    public void setDentistaId(Long dentistaId) { this.dentistaId = dentistaId; }
    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public java.time.LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(java.time.LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getProcedimento() { return procedimento; }
    public void setProcedimento(String procedimento) { this.procedimento = procedimento; }
    public java.math.BigDecimal getValor() { return valor; }
    public void setValor(java.math.BigDecimal valor) { this.valor = valor; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
