package br.com.serratec.trabalhofinalapi.dto;

import java.util.List;
import br.com.serratec.trabalhofinalapi.enums.StatusOrdemServico;
import br.com.serratec.trabalhofinalapi.model.ItemOrdemServico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OrdemServicoRequestDTO(
    @NotNull(message = "Insira o ID do cliente")
    Long clienteId,
    
    @NotNull(message = "Insira o ID do veículo")
    Long veiculoId,

    StatusOrdemServico status,

    @NotEmpty(message = "Insira pelo menos um serviço")
    @Valid
    List<ItemOrdemServico> itens
) {
    public OrdemServicoRequestDTO {
        if (status == null) {
            status = StatusOrdemServico.ABERTA; 
        }
    }
}
