package br.com.serratec.trabalhofinalapi.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.dto.VeiculoResponseDTO;
import br.com.serratec.trabalhofinalapi.exception.ResourceNotFoundException;
import br.com.serratec.trabalhofinalapi.model.Veiculo;
import br.com.serratec.trabalhofinalapi.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<VeiculoResponseDTO> listarTodos() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }
    
    public Page<VeiculoResponseDTO> listarPorPagina(Pageable pageable) {
    return veiculoRepository.findAll(pageable)
            .map(this::converterParaDTO);
    }

    public VeiculoResponseDTO buscarPorId(Long id) {
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        Veiculo entidade = veiculo.orElseThrow(() -> new ResourceNotFoundException("Veículo com ID " + id + " não encontrado."));
        return converterParaDTO(entidade);
    }

    public VeiculoResponseDTO salvar(Veiculo veiculo) {
        Veiculo salvo = veiculoRepository.save(veiculo);
        return converterParaDTO(salvo);
    }

    public void deletar(Long id) {
        if (!veiculoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Veículo com ID " + id + " não encontrado.");
        }
        veiculoRepository.deleteById(id);
    }

    private VeiculoResponseDTO converterParaDTO(Veiculo veiculo) {
        Long clienteId = null; 
        
       if (veiculo.getCliente() != null) {
            clienteId = veiculo.getCliente().getId();
        }
        

        return new VeiculoResponseDTO(
            veiculo.getId(),
            veiculo.getPlaca(),
            veiculo.getMarca(),
            veiculo.getModelo(),
            veiculo.getAno(),
            veiculo.getCor(),
            clienteId
        );
    }
}