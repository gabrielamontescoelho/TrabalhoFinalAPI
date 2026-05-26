package br.com.serratec.trabalhofinalapi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_historico_manutencao")
public class HistoricoManutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataServico;
    private String descricaoProblema;
    private String servicoRealizado;
    private Double valorTotal; 

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo; 

    public HistoricoManutencao() {}

    public HistoricoManutencao(Long id, LocalDate dataServico, String descricaoProblema, String servicoRealizado, Double valorTotal, Veiculo veiculo) {
        this.id = id;
        this.dataServico = dataServico;
        this.descricaoProblema = descricaoProblema;
        this.servicoRealizado = servicoRealizado;
        this.valorTotal = valorTotal;
        this.veiculo = veiculo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataServico() { return dataServico; }
    public void setDataServico(LocalDate dataServico) { this.dataServico = dataServico; }

    public String getDescricaoProblema() { return descricaoProblema; }
    public void setDescricaoProblema(String descricaoProblema) { this.descricaoProblema = descricaoProblema; }

    public String getServicoRealizado() { return servicoRealizado; }
    public void setServicoRealizado(String servicoRealizado) { this.servicoRealizado = servicoRealizado; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
}