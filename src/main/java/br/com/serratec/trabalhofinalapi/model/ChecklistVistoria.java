package br.com.serratec.trabalhofinalapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "checklist_vistoria")
public class ChecklistVistoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nivelCombustivel; 

    private Boolean temEstepe;

    private Boolean temArranhoes;

    private String observacoes; 

    @OneToOne
    @JoinColumn(name = "id_ordem_servico", unique = true)
    private OrdemServico ordemServico;

    public ChecklistVistoria() {
    }

    public ChecklistVistoria(Long id, String nivelCombustivel, Boolean temEstepe, Boolean temArranhoes, String observacoes, OrdemServico ordemServico) {
        this.id = id;
        this.nivelCombustivel = nivelCombustivel;
        this.temEstepe = temEstepe;
        this.temArranhoes = temArranhoes;
        this.observacoes = observacoes;
        this.ordemServico = ordemServico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivelCombustivel() {
        return nivelCombustivel;
    }

    public void setNivelCombustivel(String nivelCombustivel) {
        this.nivelCombustivel = nivelCombustivel;
    }

    public Boolean getTemEstepe() {
        return temEstepe;
    }

    public void setTemEstepe(Boolean temEstepe) {
        this.temEstepe = temEstepe;
    }

    public Boolean getTemArranhoes() {
        return temArranhoes;
    }

    public void setTemArranhoes(Boolean temArranhoes) {
        this.temArranhoes = temArranhoes;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }
}