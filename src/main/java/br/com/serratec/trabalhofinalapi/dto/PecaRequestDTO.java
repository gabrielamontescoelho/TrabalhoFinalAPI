package br.com.serratec.trabalhofinalapi.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PecaRequestDTO(

        @NotBlank(message = "Informe o nome da peça")
        String nome,

        String descricao,

        @NotNull(message = "Informe a quantidade em estoque")
        @PositiveOrZero(message = "A quantidade não pode ser negativa")
        Integer quantidadeEstoque,

        @NotNull(message = "Informe o valor unitário")
        @PositiveOrZero(message = "O valor unitário não pode ser negativo")
        BigDecimal valorUnitario
) {
}