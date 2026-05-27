package br.com.serratec.trabalhofinalapi.dto;

import br.com.serratec.trabalhofinalapi.model.Pagamento;
import br.com.serratec.trabalhofinalapi.enums.FormaPagamento;
import br.com.serratec.trabalhofinalapi.enums.StatusPagamento;

public record PagamentoResponseDTO(Long id,Double valor,FormaPagamento formaPagamento,StatusPagamento statusPagamento,Long idCliente) {
    public PagamentoResponseDTO(Pagamento pagamento ) {
        this(
            pagamento.getId(),
            pagamento.getValor(),
            pagamento.getFormaPagamento(),
            pagamento.getStatusPagamento(),
            pagamento.getCliente().getId());
    }
}
