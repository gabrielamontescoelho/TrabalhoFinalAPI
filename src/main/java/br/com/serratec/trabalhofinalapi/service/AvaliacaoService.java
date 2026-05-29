package br.com.serratec.trabalhofinalapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.dto.AvaliacaoRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.AvaliacaoResponseDTO;
import br.com.serratec.trabalhofinalapi.model.Avaliacao;
import br.com.serratec.trabalhofinalapi.model.OrdemServico;
import br.com.serratec.trabalhofinalapi.repository.AvaliacaoRepository;
import br.com.serratec.trabalhofinalapi.repository.OrdemServicoRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public AvaliacaoResponseDTO cadastrar(AvaliacaoRequestDTO dto) {

        OrdemServico ordemServico = ordemServicoRepository.findById(dto.getOrdemServicoId()).orElse(null);

        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());
        avaliacao.setOrdemServico(ordemServico);

        repository.save(avaliacao);

        AvaliacaoResponseDTO response = new AvaliacaoResponseDTO();

        response.setId(avaliacao.getId());
        response.setNota(avaliacao.getNota());
        response.setComentario(avaliacao.getComentario());

        return response;
    }
}