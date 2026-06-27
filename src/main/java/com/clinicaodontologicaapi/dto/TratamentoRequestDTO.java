package com.clinicaodontologicaapi.dto;

import jakarta.validation.constraints.*;

public class TratamentoRequestDTO {

    @NotNull(message = "PacienteId é obrigatório")
    @Positive(message = "PacienteId deve ser um ID válido (positivo)")
    private Long pacienteId;
    @NotNull(message = "DentistaId é obrigatório")
    @Positive(message = "DentistaId deve ser um ID válido (positivo)")
    private Long dentistaId;
    @NotBlank(message = "nome não pode estar em branco")
    private String nome;
    @NotBlank(message = "descricao não pode estar em branco")
    private String descricao;
    @DecimalMin(value = "0.0", message = "valor total não pode ser negativo")
    @NotNull(message = "valor total não pode ser nulo")
    private Double valorTotal;
    @Min(value = 0, message = "numero parcelas não pode ser negativo")
    @NotNull(message = "numero parcelas não pode ser nulo")
    private Integer numeroParcelas;
    @NotBlank(message = "status não pode estar em branco")
    private String status;
    @FutureOrPresent(message = "data inicio não pode ser retroativo")
    @NotNull(message = "data inicio não pode ser nulo")
    private java.time.LocalDateTime dataInicio;

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getDentistaId() { return dentistaId; }
    public void setDentistaId(Long dentistaId) { this.dentistaId = dentistaId; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public Integer getNumeroParcelas() { return numeroParcelas; }
    public void setNumeroParcelas(Integer numeroParcelas) { this.numeroParcelas = numeroParcelas; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public java.time.LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(java.time.LocalDateTime dataInicio) { this.dataInicio = dataInicio; }
}
