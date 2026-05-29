package br.com.serratec.trabalhofinalapi.dto;

import java.math.BigDecimal;

import br.com.serratec.trabalhofinalapi.model.Peca;

public record PecaResponseDTO(
        Long id,
        String nome,
        String descricao,
        Integer quantidadeEstoque,
        BigDecimal valorUnitario
) {
    public PecaResponseDTO(Peca peca) {
        this(
                peca.getId(),
                peca.getNome(),
                peca.getDescricao(),
                peca.getQuantidadeEstoque(),
                peca.getValorUnitario()
        );
    }
}