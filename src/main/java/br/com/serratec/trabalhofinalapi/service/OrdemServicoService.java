package br.com.serratec.trabalhofinalapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.dto.ClienteResponseDto;
import br.com.serratec.trabalhofinalapi.dto.ItemOrdemServicoResponseDTO;
import br.com.serratec.trabalhofinalapi.dto.OrdemServicoRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.OrdemServicoResponseDTO;
import br.com.serratec.trabalhofinalapi.dto.VeiculoResponseDTO;
import br.com.serratec.trabalhofinalapi.enums.StatusOrdemServico;
import br.com.serratec.trabalhofinalapi.model.OrdemServico;
import br.com.serratec.trabalhofinalapi.model.Servico;
import br.com.serratec.trabalhofinalapi.model.Veiculo;
import br.com.serratec.trabalhofinalapi.repository.ClienteRepository;
import br.com.serratec.trabalhofinalapi.repository.OrdemServicoRepository;
import br.com.serratec.trabalhofinalapi.repository.ServicoRepository;
import br.com.serratec.trabalhofinalapi.repository.VeiculoRepository;
import br.com.serratec.trabalhofinalapi.model.Cliente;
import br.com.serratec.trabalhofinalapi.model.ItemOrdemServico;
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

        @Transactional
        public OrdemServicoResponseDTO inserir(OrdemServicoRequestDTO dto) {
        OrdemServico os = new OrdemServico();
        os.setStatus(StatusOrdemServico.ABERTA);

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        Veiculo veiculo = veiculoRepository.findById(dto.veiculoId())
                        .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));
        os.setCliente(cliente);
        os.setVeiculo(veiculo);

        List<ItemOrdemServico> listaItens = new ArrayList<>();

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

        os.setItens(listaItens);

        OrdemServico osSalva = ordemRepository.save(os);

        List<ItemOrdemServicoResponseDTO> itemDto = osSalva.getItens().stream()
                        .map(item -> new ItemOrdemServicoResponseDTO(
                                        item.getId(),
                                        item.getServico().getId(),
                                        item.getQuantidade(),
                                        item.getDesconto(),
                                        item.getSubtotal()))
                        .toList();

        ClienteResponseDto clienteDto = new ClienteResponseDto(osSalva.getCliente());

        VeiculoResponseDTO veiculoDto = new VeiculoResponseDTO(
                        osSalva.getVeiculo().getId(),
                        osSalva.getVeiculo().getPlaca(),
                        osSalva.getVeiculo().getMarca(),
                        osSalva.getVeiculo().getModelo(),
                        osSalva.getVeiculo().getAno(),
                        osSalva.getVeiculo().getCor(),
                        osSalva.getCliente().getId(),
                        osSalva.getCliente().getNome(),
                        osSalva.getCliente().getEmail(),
                        osSalva.getCliente().getTelefone());

        return new OrdemServicoResponseDTO(
                        osSalva.getId(),
                        osSalva.getStatus(),
                        osSalva.getValorTotal(),
                        clienteDto,
                        veiculoDto,
                        itemDto);
}

        public OrdemServicoResponseDTO editar(Long id, OrdemServicoRequestDTO dto) {
                OrdemServico ordemServicoBanco = ordemRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada!"));
                if (dto.status() != null) {
                        ordemServicoBanco.setStatus(dto.status());
                }
                Cliente cliente = clienteRepository.findById(dto.clienteId())
                                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
                Veiculo veiculo = veiculoRepository.findById(dto.veiculoId())
                                .orElseThrow(() -> new RuntimeException("Veículo não encontrado!"));

                ordemServicoBanco.setCliente(cliente);
                ordemServicoBanco.setVeiculo(veiculo);

                List<ItemOrdemServico> listaItens = new ArrayList<>();

                for (ItemOrdemServico item : dto.itens()) {
                        item.setOrdemServico(ordemServicoBanco);
                        Servico servico = servicoRepository.findById(item.getServico().getId())
                                        .orElseThrow(() -> new RuntimeException("Serviço não encontrado!"));
                        item.setServico(servico);

                        listaItens.add(item);
                }

                ordemServicoBanco.getItens().clear();
                ordemServicoBanco.getItens().addAll(listaItens);

                OrdemServico osSalva = ordemRepository.save(ordemServicoBanco);

                List<ItemOrdemServicoResponseDTO> itemDto = osSalva.getItens().stream()
                                .map(item -> new ItemOrdemServicoResponseDTO(
                                                item.getId(),
                                                item.getServico().getId(),
                                                item.getQuantidade(),
                                                item.getDesconto(),
                                                item.getSubtotal()))
                                .toList();

                ClienteResponseDto clienteDto = new ClienteResponseDto(osSalva.getCliente());

                VeiculoResponseDTO veiculoDto = new VeiculoResponseDTO(
                                osSalva.getVeiculo().getId(),
                                osSalva.getVeiculo().getPlaca(),
                                osSalva.getVeiculo().getMarca(),
                                osSalva.getVeiculo().getModelo(),
                                osSalva.getVeiculo().getAno(),
                                osSalva.getVeiculo().getCor(),
                                osSalva.getCliente().getId(),
                                osSalva.getCliente().getNome(),
                                osSalva.getCliente().getEmail(),
                                osSalva.getCliente().getTelefone());

                return new OrdemServicoResponseDTO(
                                osSalva.getId(),
                                osSalva.getStatus(),
                                osSalva.getValorTotal(),
                                clienteDto,
                                veiculoDto,
                                itemDto);

        }

        public OrdemServicoResponseDTO buscar(Long id) {
                OrdemServico os = ordemRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada!"));

                List<ItemOrdemServicoResponseDTO> itemDto = os.getItens().stream()
                                .map(item -> new ItemOrdemServicoResponseDTO(
                                                item.getId(),
                                                item.getServico().getId(),
                                                item.getQuantidade(),
                                                item.getDesconto(),
                                                item.getSubtotal()))
                                .toList();
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

                return new OrdemServicoResponseDTO(id, os.getStatus(), os.getValorTotal(), clienteDto, veiculoDto,
                                itemDto);
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

                List<ItemOrdemServicoResponseDTO> itemDto = osSalva.getItens().stream()
                                .map(item -> new ItemOrdemServicoResponseDTO(
                                                item.getId(),
                                                item.getServico().getId(),
                                                item.getQuantidade(),
                                                item.getDesconto(),
                                                item.getSubtotal()))
                                .toList();

                ClienteResponseDto clienteDto = new ClienteResponseDto(osSalva.getCliente());

                VeiculoResponseDTO veiculoDto = new VeiculoResponseDTO(
                                osSalva.getVeiculo().getId(),
                                osSalva.getVeiculo().getPlaca(),
                                osSalva.getVeiculo().getMarca(),
                                osSalva.getVeiculo().getModelo(),
                                osSalva.getVeiculo().getAno(),
                                osSalva.getVeiculo().getCor(),
                                osSalva.getCliente().getId(),
                                osSalva.getCliente().getNome(),
                                osSalva.getCliente().getEmail(),
                                osSalva.getCliente().getTelefone());

                return new OrdemServicoResponseDTO(
                                osSalva.getId(),
                                osSalva.getStatus(),
                                osSalva.getValorTotal(),
                                clienteDto,
                                veiculoDto,
                                itemDto);
        }

        public Page<OrdemServicoResponseDTO> listarTodasPaginada(Pageable pageable) {
                Page<OrdemServico> paginaEntidades = ordemRepository.findAll(pageable);

                return paginaEntidades.map(os -> {

                        List<ItemOrdemServicoResponseDTO> itensResponse = os.getItens().stream()
                                        .map(item -> new ItemOrdemServicoResponseDTO(
                                                        item.getId(),
                                                        item.getServico().getId(),
                                                        item.getQuantidade(),
                                                        item.getDesconto(),
                                                        item.getSubtotal()))
                                        .toList();

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
                                        itensResponse);
                });
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
}
