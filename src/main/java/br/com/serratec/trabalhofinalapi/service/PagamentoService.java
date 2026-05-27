package br.com.serratec.trabalhofinalapi.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.dto.PagamentoRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.PagamentoResponseDTO;
import br.com.serratec.trabalhofinalapi.dto.RelatorioFaturamentoDto;
import br.com.serratec.trabalhofinalapi.enums.StatusPagamento;
import br.com.serratec.trabalhofinalapi.model.Cliente;
import br.com.serratec.trabalhofinalapi.model.Pagamento;
import br.com.serratec.trabalhofinalapi.repository.ClienteRepository;
import br.com.serratec.trabalhofinalapi.repository.PagamentoRepository;
import jakarta.transaction.Transactional;

@Service
public class PagamentoService {
    @Autowired 
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public PagamentoResponseDTO inserir(PagamentoRequestDTO dto){
        Cliente cliente = clienteRepository.findById(dto.idCliente()).get();

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(dto.valor());
        pagamento.setFormaPagamento(dto.formaPagamento());
        pagamento.setStatusPagamento(dto.statusPagamento());
        pagamento.setCliente(cliente);
        pagamento.setDataPagamento(LocalDate.now());

        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);

        return new PagamentoResponseDTO(pagamentoSalvo);
    }

    public List<PagamentoResponseDTO> listar(){
        return pagamentoRepository.findAll().stream().map(PagamentoResponseDTO::new).toList();
    }

    public Optional<Pagamento> buscarPorId(Long id){
        return pagamentoRepository.findById(id);
    }

    public PagamentoResponseDTO editar(Long id, PagamentoRequestDTO dto){
        Pagamento pagamento = pagamentoRepository.findById(id).get();

        Cliente cliente = clienteRepository.findById(dto.idCliente()).get();

        pagamento.setValor(dto.valor());
        pagamento.setFormaPagamento(dto.formaPagamento());
        pagamento.setStatusPagamento(dto.statusPagamento());
        pagamento.setCliente(cliente);

        Pagamento pagamentoAtualizado = pagamentoRepository.save(pagamento);

        return new PagamentoResponseDTO(pagamentoAtualizado);

    }

    public void deletar(Long id){
        pagamentoRepository.deleteById(id);
    }

    public RelatorioFaturamentoDto gerarRelatorioFaturamento() {

        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        double totalFaturado = pagamentos.stream().filter(p -> p.getStatusPagamento() == StatusPagamento.PAGO).mapToDouble(Pagamento::getValor).sum();

        long quantidadePagamentos = pagamentos.stream().filter(p -> p.getStatusPagamento() == StatusPagamento.PAGO).count();

        return new RelatorioFaturamentoDto(totalFaturado, quantidadePagamentos);
    }

}
