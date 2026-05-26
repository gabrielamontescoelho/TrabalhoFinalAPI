package br.com.serratec.trabalhofinalapi.dto;

import java.time.LocalDate;

public class HistoricoRequestDTO {
    private LocalDate dataServico;
    private String descricaoProblema;
    private String servicoRealizado;
    private Double valorTotal;
    private Long veiculoId; 

    public HistoricoRequestDTO() {}

    public HistoricoRequestDTO(LocalDate dataServico, String descricaoProblema, String servicoRealizado, Double valorTotal, Long veiculoId) {
        this.dataServico = dataServico;
        this.descricaoProblema = descricaoProblema;
        this.servicoRealizado = servicoRealizado;
        this.valorTotal = valorTotal;
        this.veiculoId = veiculoId;
    }

    public LocalDate getDataServico() { return dataServico; }
    public void setDataServico(LocalDate dataServico) { this.dataServico = dataServico; }

    public String getDescricaoProblema() { return descricaoProblema; }
    public void setDescricaoProblema(String descricaoProblema) { this.descricaoProblema = descricaoProblema; }

    public String getServicoRealizado() { return servicoRealizado; }
    public void setServicoRealizado(String servicoRealizado) { this.servicoRealizado = servicoRealizado; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public Long getVeiculoId() { return veiculoId; }
    public void setVeiculoId(Long veiculoId) { this.veiculoId = veiculoId; }
}