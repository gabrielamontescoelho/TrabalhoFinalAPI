package br.com.serratec.trabalhofinalapi.model;

import java.math.BigDecimal;
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

    @ManyToOne
    @JoinColumn(name = "id_servico", nullable = false)
    private Servico servico;

    private Integer quantidade;
    private BigDecimal valorServico;
    private BigDecimal desconto;
    private BigDecimal subtotal;

    public ItemOrdemServico() {
    }

    public ItemOrdemServico(Long id, OrdemServico ordemServico, Servico servico, Integer quantidade,
            BigDecimal valorServico, BigDecimal desconto, BigDecimal subtotal) {
        this.id = id;
        this.ordemServico = ordemServico;
        this.servico = servico;
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

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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
