package br.com.serratec.trabalhofinalapi.dto;

import java.math.BigDecimal;

public record ItemOrdemServicoResponseDTO(
        Long id,
        Long servicoId,
        Integer quantidade,
        BigDecimal desconto,
        BigDecimal subtotal
) {

}
