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

    AvaliacaoResponseDTO avaliacao

>>>>>>> main
) {

}

public OrdemServicoResponseDTO toDTO(OrdemServico ordemServico) {

    AvaliacaoResponseDTO avaliacaoDTO = null;

    if (ordemServico.getAvaliacao() != null) {

        avaliacaoDTO = new AvaliacaoResponseDTO(
            ordemServico.getAvaliacao().getId(),
            ordemServico.getAvaliacao().getNota(),
            ordemServico.getAvaliacao().getComentario()
        );
    }

    
    return new OrdemServicoResponseDTO(
        ordemServico.getId(),
        ordemServico.getStatus(),
        ordemServico.getValorTotal(),
        new ClienteResponseDto(ordemServico.getCliente()),
        new VeiculoResponseDTO(ordemServico.getVeiculo()),
        ordemServico.getItens()
            .stream()
            .map(ItemOrdemServicoResponseDTO::new)
            .toList(),


        avaliacaoDTO
    );
}