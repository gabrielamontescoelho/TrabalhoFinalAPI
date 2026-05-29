package br.com.serratec.trabalhofinalapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.dto.PecaRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.PecaResponseDTO;
import br.com.serratec.trabalhofinalapi.exception.ResourceNotFoundException;
import br.com.serratec.trabalhofinalapi.model.Peca;
import br.com.serratec.trabalhofinalapi.repository.PecaRepository;

@Service
public class PecaService {

    @Autowired
    private PecaRepository repository;

    public PecaResponseDTO inserir(PecaRequestDTO dto) {
        Peca peca = new Peca();
        peca.setNome(dto.nome());
        peca.setDescricao(dto.descricao());
        peca.setQuantidadeEstoque(dto.quantidadeEstoque());
        peca.setValorUnitario(dto.valorUnitario());

        Peca pecaSalva = repository.save(peca);

        return new PecaResponseDTO(pecaSalva);
    }

    public Page<PecaResponseDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(PecaResponseDTO::new);
    }

    public Peca buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Peça não encontrada"));
    }

    public PecaResponseDTO buscarPorId(Long id) {
        Peca peca = buscarEntidadePorId(id);
        return new PecaResponseDTO(peca);
    }

    public PecaResponseDTO editar(Long id, PecaRequestDTO dto) {
        Peca peca = buscarEntidadePorId(id);

        peca.setNome(dto.nome());
        peca.setDescricao(dto.descricao());
        peca.setQuantidadeEstoque(dto.quantidadeEstoque());
        peca.setValorUnitario(dto.valorUnitario());

        Peca pecaAtualizada = repository.save(peca);

        return new PecaResponseDTO(pecaAtualizada);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Peça não encontrada");
        }

        repository.deleteById(id);
    }
}