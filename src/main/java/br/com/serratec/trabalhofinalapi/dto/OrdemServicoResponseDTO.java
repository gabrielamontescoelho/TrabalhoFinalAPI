package br.com.serratec.trabalhofinalapi.dto;

import java.math.BigDecimal;
import java.util.List;
import br.com.serratec.trabalhofinalapi.enums.StatusOrdemServico;

public record OrdemServicoResponseDTO(
    
    Long id,
    StatusOrdemServico status,
    BigDecimal valorTotal,       
    ClienteResponseDto cliente,
    VeiculoResponseDTO veiculo,
    List<ItemOrdemServicoResponseDTO> itens,
    List<ItemPecaOrdemServicoResponseDTO> itensPeca
) {

}
