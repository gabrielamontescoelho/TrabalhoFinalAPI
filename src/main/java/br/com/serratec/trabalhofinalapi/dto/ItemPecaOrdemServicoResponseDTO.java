package br.com.serratec.trabalhofinalapi.dto;

import java.math.BigDecimal;

public record ItemPecaOrdemServicoResponseDTO(
        Long id,
        Long pecaId,
        String nomePeca,
        Integer quantidade,
        BigDecimal desconto,
        BigDecimal subtotal
) {
}