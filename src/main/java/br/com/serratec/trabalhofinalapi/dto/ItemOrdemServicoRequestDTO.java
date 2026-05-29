package br.com.serratec.trabalhofinalapi.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemOrdemServicoRequestDTO(
    @NotNull(message = "Insira o ID do serviço")
    Long servicoId,
    
    @NotNull(message = "Insira a quantidade")
    @Positive(message = "A quantidade deve ser maior que zero")
    Integer quantidade,
    
    @NotNull(message = "Insira o valor do desconto (envie 0 se não houver)")
    BigDecimal desconto
) {

}
