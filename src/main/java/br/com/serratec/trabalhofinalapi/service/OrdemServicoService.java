package br.com.serratec.trabalhofinalapi.service;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.dto.ClienteResponseDto;
import br.com.serratec.trabalhofinalapi.dto.ItemOrdemServicoResponseDTO;
import br.com.serratec.trabalhofinalapi.dto.OrdemServicoRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.OrdemServicoResponseDTO;
import br.com.serratec.trabalhofinalapi.dto.VeiculoResponseDTO;
import br.com.serratec.trabalhofinalapi.model.Cliente;
import br.com.serratec.trabalhofinalapi.model.OrdemServico;
import br.com.serratec.trabalhofinalapi.repository.ClienteRepository;
import br.com.serratec.trabalhofinalapi.repository.OrdemServicoRepository;
import br.com.serratec.trabalhofinalapi.repository.ServicoRepository;
import br.com.serratec.trabalhofinalapi.repository.VeiculoRepository;

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

    public OrdemServicoResponseDTO inserir(OrdemServicoRequestDTO dto) {
        return null;
    }

    public OrdemServicoResponseDTO buscar(Long id) {
        OrdemServico os = ordemRepository.findById(id).orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada!"));  
        
        List<ItemOrdemServicoResponseDTO> itemDto = os.getItens().stream().map(item -> new ItemOrdemServicoResponseDTO(
            item.getId(),
            item.getServico().getId(),
            item.getQuantidade(),
            item.getValorServico(),
            item.getDesconto(),
            item.getSubtotal()
        )).toList();
        ClienteResponseDto clienteDto = new ClienteResponseDto(os.getCliente());
    }
}
