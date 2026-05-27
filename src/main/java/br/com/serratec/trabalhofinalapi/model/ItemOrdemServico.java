package br.com.serratec.trabalhofinalapi.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_ordem_servico")
public class ItemOrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_ordem_servico")
    private OrdemServico ordemServico;

    // colocar o relacionament com servico dps
    @Column(name = "servico_id")
    private Long servicoId;

    private Integer quantidade;
    private BigDecimal valorServico;
    private BigDecimal desconto;
    private BigDecimal subtotal;

    public ItemOrdemServico() {
    }

    public ItemOrdemServico(Long id, OrdemServico ordemServico, Long servicoId, Integer quantidade,
            BigDecimal valorServico, BigDecimal desconto, BigDecimal subtotal) {
        this.id = id;
        this.ordemServico = ordemServico;
        this.servicoId = servicoId;
        this.quantidade = quantidade;
        this.valorServico = valorServico;
        this.desconto = desconto;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Long getServicoId() {
        return servicoId;
    }

    public void setServicoId(Long servicoId) {
        this.servicoId = servicoId;
    }

    public Integer getQuantiidade() {
        return quantidade;
    }

    public void setQuantiidade(Integer quantiidade) {
        this.quantidade = quantiidade;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

}
