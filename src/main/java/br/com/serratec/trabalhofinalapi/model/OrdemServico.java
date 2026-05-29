package br.com.serratec.trabalhofinalapi.model;

import java.math.BigDecimal;
import java.util.List;

import br.com.serratec.trabalhofinalapi.enums.StatusOrdemServico;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordem_servico")
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOrdemServico> itens;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
private List<ItemPecaOrdemServico> itensPeca;

    private BigDecimal valorTotal;

    public OrdemServico() {
    }

    public OrdemServico(Long id, Cliente cliente, Veiculo veiculo, StatusOrdemServico status,
            List<ItemOrdemServico> itens) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.status = status;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public BigDecimal getValorTotal() {
        BigDecimal soma = BigDecimal.ZERO;

        if (this.itens != null) {
            for (ItemOrdemServico itemOrdemServico : this.itens) {
                soma = soma.add(itemOrdemServico.getSubtotal());
            }
        }
        if (this.itensPeca != null) {
            for (ItemPecaOrdemServico itemPecaOrdemServico : this.itensPeca) {
                soma = soma.add(itemPecaOrdemServico.getSubtotal());
            }
        }               

        this.valorTotal = soma;
        return this.valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public List<ItemOrdemServico> getItens() {
        return itens;
    }

    public void setItens(List<ItemOrdemServico> itens) {
        this.itens = itens;
    }
    public List<ItemPecaOrdemServico> getItensPeca() {
    return itensPeca;
}

    public void setItensPeca(List<ItemPecaOrdemServico> itensPeca) {
    this.itensPeca = itensPeca;
}
}
