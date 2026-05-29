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
@Table(name = "item_peca_ordem_servico")
public class ItemPecaOrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_ordem_servico")
    private OrdemServico ordemServico;

    @ManyToOne
    @JoinColumn(name = "id_peca", nullable = false)
    private Peca peca;

    private Integer quantidade;
    private BigDecimal desconto;
    private BigDecimal subtotal;

    public ItemPecaOrdemServico() {
    }

    public ItemPecaOrdemServico(Long id, OrdemServico ordemServico, Peca peca, Integer quantidade,
            BigDecimal desconto, BigDecimal subtotal) {
        this.id = id;
        this.ordemServico = ordemServico;
        this.peca = peca;
        this.quantidade = quantidade;
        this.desconto = desconto;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public Peca getPeca() {
        return peca;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public BigDecimal getSubtotal() {
        BigDecimal descontoItem = (this.desconto == null) ? BigDecimal.ZERO : this.desconto;

        this.subtotal = this.peca.getValorUnitario()
                .multiply(BigDecimal.valueOf(this.quantidade))
                .subtract(descontoItem);

        return this.subtotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}