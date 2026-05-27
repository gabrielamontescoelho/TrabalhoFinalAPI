package br.com.serratec.trabalhofinalapi.dto;

import br.com.serratec.trabalhofinalapi.enums.FormaPagamento;
import br.com.serratec.trabalhofinalapi.enums.StatusPagamento;
import br.com.serratec.trabalhofinalapi.model.Pagamento;

public record PagamentoRequestDTO(
    Double valor,
    FormaPagamento formaPagamento,
    StatusPagamento statusPagamento,
    Long idCliente
) {

    public PagamentoRequestDTO(Pagamento pagamento ) {
        this(
            pagamento.getValor(),
            pagamento.getFormaPagamento(),
            pagamento.getStatusPagamento(),
            pagamento.getCliente().getId());
    }
    
}
