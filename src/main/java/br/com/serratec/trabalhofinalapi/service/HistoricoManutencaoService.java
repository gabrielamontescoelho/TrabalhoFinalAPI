package br.com.serratec.trabalhofinalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.dto.HistoricoRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.RelatorioOficinaDTO;
import br.com.serratec.trabalhofinalapi.exception.ResourceNotFoundException;
import br.com.serratec.trabalhofinalapi.model.HistoricoManutencao;
import br.com.serratec.trabalhofinalapi.model.Veiculo;
import br.com.serratec.trabalhofinalapi.repository.HistoricoManutencaoRepository;
import br.com.serratec.trabalhofinalapi.repository.VeiculoRepository;

@Service
public class HistoricoManutencaoService {

    @Autowired
    private HistoricoManutencaoRepository historicoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<HistoricoManutencao> listarTodos() {
        return historicoRepository.findAll();
    }

    public HistoricoManutencao salvar(HistoricoRequestDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado para o ID: " + dto.getVeiculoId()));

        HistoricoManutencao historico = new HistoricoManutencao();
        historico.setDataServico(dto.getDataServico());
        historico.setDescricaoProblema(dto.getDescricaoProblema());
        historico.setServicoRealizado(dto.getServicoRealizado());
        historico.setValorTotal(dto.getValorTotal());
        historico.setVeiculo(veiculo);

        return historicoRepository.save(historico);
    }

    public HistoricoManutencao atualizar(Long id, HistoricoRequestDTO dto) {
        HistoricoManutencao antigo = historicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Histórico não encontrado."));

        antigo.setDataServico(dto.getDataServico());
        antigo.setDescricaoProblema(dto.getDescricaoProblema());
        antigo.setServicoRealizado(dto.getServicoRealizado());
        antigo.setValorTotal(dto.getValorTotal());

        return historicoRepository.save(antigo);
    }

    public void deletar(Long id) {
        if (!historicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Histórico não encontrado.");
        }
        historicoRepository.deleteById(id);
    }

    public RelatorioOficinaDTO obterRelatorioDados(Long veiculoId) {
        String modeloTopDefeito = historicoRepository.buscarModeloComMaisDefeitos();
        Double mediaDoCarro = historicoRepository.calcularMediaGastosPorVeiculo(veiculoId);

        if (modeloTopDefeito == null) modeloTopDefeito = "Nenhum dado registrado";
        if (mediaDoCarro == null) mediaDoCarro = 0.0;

        return new RelatorioOficinaDTO(modeloTopDefeito, mediaDoCarro);
    }
}

