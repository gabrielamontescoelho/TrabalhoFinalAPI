package br.com.serratec.trabalhofinalapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.dto.ClienteResponseDto;
import br.com.serratec.trabalhofinalapi.dto.ItemOrdemServicoResponseDTO;
import br.com.serratec.trabalhofinalapi.dto.ItemPecaOrdemServicoResponseDTO;
import br.com.serratec.trabalhofinalapi.dto.OrdemServicoRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.OrdemServicoResponseDTO;
import br.com.serratec.trabalhofinalapi.dto.VeiculoResponseDTO;
import br.com.serratec.trabalhofinalapi.enums.StatusOrdemServico;
import br.com.serratec.trabalhofinalapi.model.Cliente;
import br.com.serratec.trabalhofinalapi.model.ItemOrdemServico;
import br.com.serratec.trabalhofinalapi.model.ItemPecaOrdemServico;
import br.com.serratec.trabalhofinalapi.model.OrdemServico;
import br.com.serratec.trabalhofinalapi.model.Peca;
import br.com.serratec.trabalhofinalapi.model.Servico;
import br.com.serratec.trabalhofinalapi.model.Veiculo;
import br.com.serratec.trabalhofinalapi.repository.ClienteRepository;
import br.com.serratec.trabalhofinalapi.repository.OrdemServicoRepository;
import br.com.serratec.trabalhofinalapi.repository.PecaRepository;
import br.com.serratec.trabalhofinalapi.repository.ServicoRepository;
import br.com.serratec.trabalhofinalapi.repository.VeiculoRepository;
import jakarta.transaction.Transactional;

@Service
public class OrdemServicoService {

        @Autowired
        private OrdemServicoRepository ordemRepository;

        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private VeiculoRepository veiculoRepository;

        @Autowired
        private ServicoRepository servicoRepository;

        @Autowired
        private PecaRepository pecaRepository;

        @Transactional
        public OrdemServicoResponseDTO inserir(OrdemServicoRequestDTO dto) {
                OrdemServico os = new OrdemServico();
                os.setStatus(dto.status() != null ? dto.status() : StatusOrdemServico.ABERTA);

                Cliente cliente = clienteRepository.findById(dto.clienteId())
                                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

                Veiculo veiculo = veiculoRepository.findById(dto.veiculoId())
                                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));

                os.setCliente(cliente);
                os.setVeiculo(veiculo);

                List<ItemOrdemServico> listaItens = montarItensServico(dto, os);
                List<ItemPecaOrdemServico> listaItensPeca = montarItensPeca(dto, os);

                os.setItens(listaItens);
                os.setItensPeca(listaItensPeca);

                OrdemServico osSalva = ordemRepository.save(os);

                return converterParaDTO(osSalva);
        }

        @Transactional
        public OrdemServicoResponseDTO editar(Long id, OrdemServicoRequestDTO dto) {
                OrdemServico os = ordemRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada!"));

                if (dto.status() != null) {
                        os.setStatus(dto.status());
                }

                Cliente cliente = clienteRepository.findById(dto.clienteId())
                                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

                Veiculo veiculo = veiculoRepository.findById(dto.veiculoId())
                                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));

                os.setCliente(cliente);
                os.setVeiculo(veiculo);

                List<ItemOrdemServico> listaItens = montarItensServico(dto, os);
                List<ItemPecaOrdemServico> listaItensPeca = montarItensPeca(dto, os);

                if (os.getItens() == null) {
                        os.setItens(new ArrayList<>());
                }

                if (os.getItensPeca() == null) {
                        os.setItensPeca(new ArrayList<>());
                }

                os.getItens().clear();
                os.getItens().addAll(listaItens);

                os.getItensPeca().clear();
                os.getItensPeca().addAll(listaItensPeca);

                OrdemServico osSalva = ordemRepository.save(os);

                return converterParaDTO(osSalva);
        }

        public OrdemServicoResponseDTO buscar(Long id) {
                OrdemServico os = ordemRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada!"));

                return converterParaDTO(os);
        }

        @Transactional
        public OrdemServicoResponseDTO alterarStatus(Long id, StatusOrdemServico novoStatus) {
                OrdemServico os = ordemRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada!"));

                if (os.getStatus() == StatusOrdemServico.CANCELADA) {
                        throw new RuntimeException("Não é possível alterar o status de uma ordem cancelada!");
                }

                os.setStatus(novoStatus);

                OrdemServico osSalva = ordemRepository.save(os);

                return converterParaDTO(osSalva);
        }

        public Page<OrdemServicoResponseDTO> listarTodasPaginada(Pageable pageable) {
                Page<OrdemServico> paginaEntidades = ordemRepository.findAll(pageable);

                return paginaEntidades.map(this::converterParaDTO);
        }

        @Transactional
        public void deletar(Long id) {
                OrdemServico os = ordemRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada!"));

                if (os.getStatus() == StatusOrdemServico.FINALIZADA) {
                        throw new RuntimeException("Não é permitido deletar uma Ordem de Serviço já finalizada!");
                }

                ordemRepository.delete(os);
        }

        private List<ItemOrdemServico> montarItensServico(OrdemServicoRequestDTO dto, OrdemServico os) {
                List<ItemOrdemServico> listaItens = new ArrayList<>();

                if (dto.itens() != null) {
                        for (ItemOrdemServico itemDto : dto.itens()) {
                                ItemOrdemServico novoItem = new ItemOrdemServico();

                                novoItem.setOrdemServico(os);
                                novoItem.setQuantidade(itemDto.getQuantidade());
                                novoItem.setDesconto(itemDto.getDesconto());

                                Servico servico = servicoRepository.findById(itemDto.getServico().getId())
                                                .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));

                                novoItem.setServico(servico);

                                listaItens.add(novoItem);
                        }
                }

                return listaItens;
        }

        private List<ItemPecaOrdemServico> montarItensPeca(OrdemServicoRequestDTO dto, OrdemServico os) {
                List<ItemPecaOrdemServico> listaItensPeca = new ArrayList<>();

                if (dto.itensPeca() != null) {
                        for (ItemPecaOrdemServico itemPecaDto : dto.itensPeca()) {
                                ItemPecaOrdemServico novoItemPeca = new ItemPecaOrdemServico();

                                novoItemPeca.setOrdemServico(os);
                                novoItemPeca.setQuantidade(itemPecaDto.getQuantidade());
                                novoItemPeca.setDesconto(itemPecaDto.getDesconto());

                                Peca peca = pecaRepository.findById(itemPecaDto.getPeca().getId())
                                                .orElseThrow(() -> new RuntimeException("Peça não encontrada!"));

                                novoItemPeca.setPeca(peca);

                                listaItensPeca.add(novoItemPeca);
                        }
                }

                return listaItensPeca;
        }

        private OrdemServicoResponseDTO converterParaDTO(OrdemServico os) {
                List<ItemOrdemServicoResponseDTO> itensServicoDto = converterItensServico(os);
                List<ItemPecaOrdemServicoResponseDTO> itensPecaDto = converterItensPeca(os);

                ClienteResponseDto clienteDto = new ClienteResponseDto(os.getCliente());

                VeiculoResponseDTO veiculoDto = new VeiculoResponseDTO(
                                os.getVeiculo().getId(),
                                os.getVeiculo().getPlaca(),
                                os.getVeiculo().getMarca(),
                                os.getVeiculo().getModelo(),
                                os.getVeiculo().getAno(),
                                os.getVeiculo().getCor(),
                                os.getCliente().getId(),
                                os.getCliente().getNome(),
                                os.getCliente().getEmail(),
                                os.getCliente().getTelefone());

                return new OrdemServicoResponseDTO(
                                os.getId(),
                                os.getStatus(),
                                os.getValorTotal(),
                                clienteDto,
                                veiculoDto,
                                itensServicoDto,
                                itensPecaDto);
        }

        private List<ItemOrdemServicoResponseDTO> converterItensServico(OrdemServico os) {
                if (os.getItens() == null) {
                        return List.of();
                }

                return os.getItens().stream()
                                .map(item -> new ItemOrdemServicoResponseDTO(
                                                item.getId(),
                                                item.getServico().getId(),
                                                item.getQuantidade(),
                                                item.getDesconto(),
                                                item.getSubtotal()))
                                .toList();
        }

        private List<ItemPecaOrdemServicoResponseDTO> converterItensPeca(OrdemServico os) {
                if (os.getItensPeca() == null) {
                        return List.of();
                }

                return os.getItensPeca().stream()
                                .map(item -> new ItemPecaOrdemServicoResponseDTO(
                                                item.getId(),
                                                item.getPeca().getId(),
                                                item.getPeca().getNome(),
                                                item.getQuantidade(),
                                                item.getDesconto(),
                                                item.getSubtotal()))
                                .toList();
        }
}