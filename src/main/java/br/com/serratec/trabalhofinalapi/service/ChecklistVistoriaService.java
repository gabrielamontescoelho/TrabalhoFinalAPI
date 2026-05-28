package br.com.serratec.trabalhofinalapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.dto.ChecklistVistoriaRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.ChecklistVistoriaResponseDTO;
import br.com.serratec.trabalhofinalapi.model.ChecklistVistoria;
import br.com.serratec.trabalhofinalapi.model.OrdemServico;
import br.com.serratec.trabalhofinalapi.repository.ChecklistVistoriaRepository;
import br.com.serratec.trabalhofinalapi.repository.OrdemServicoRepository;
import jakarta.transaction.Transactional;

@Service
public class ChecklistVistoriaService {

    @Autowired
    private ChecklistVistoriaRepository checklistRepository;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Transactional
    public ChecklistVistoriaResponseDTO inserir(ChecklistVistoriaRequestDTO dto) {
        OrdemServico os = ordemServicoRepository.findById(dto.ordemServicoId())
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço com ID " + dto.ordemServicoId() + " não encontrada."));

        ChecklistVistoria checklist = new ChecklistVistoria();
        checklist.setNivelCombustivel(dto.nivelCombustivel());
        checklist.setTemEstepe(dto.temEstepe());
        checklist.setTemArranhoes(dto.temArranhoes());
        checklist.setObservacoes(dto.observacoes());
        checklist.setOrdemServico(os); 

        checklist = checklistRepository.save(checklist);

        return new ChecklistVistoriaResponseDTO(checklist);
    }

    public List<ChecklistVistoriaResponseDTO> listarTodos() {
        List<ChecklistVistoria> lista = checklistRepository.findAll();

        return lista.stream()
                .map(ChecklistVistoriaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ChecklistVistoriaResponseDTO buscarPorId(Long id) {
        ChecklistVistoria checklist = checklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Checklist de Vistoria com ID " + id + " não encontrado."));
        
        return new ChecklistVistoriaResponseDTO(checklist);
    }

    @Transactional
    public ChecklistVistoriaResponseDTO atualizar(Long id, ChecklistVistoriaRequestDTO dto) {

        ChecklistVistoria checklistExistente = checklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Checklist de Vistoria com ID " + id + " não encontrado."));

        OrdemServico os = ordemServicoRepository.findById(dto.ordemServicoId())
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço com ID " + dto.ordemServicoId() + " não encontrada."));

        checklistExistente.setNivelCombustivel(dto.nivelCombustivel());
        checklistExistente.setTemEstepe(dto.temEstepe());
        checklistExistente.setTemArranhoes(dto.temArranhoes());
        checklistExistente.setObservacoes(dto.observacoes());
        checklistExistente.setOrdemServico(os);

        checklistExistente = checklistRepository.save(checklistExistente);
        
        return new ChecklistVistoriaResponseDTO(checklistExistente);
    }

    @Transactional
    public void deletar(Long id) {
        if (!checklistRepository.existsById(id)) {
            throw new RuntimeException("Checklist de Vistoria com ID " + id + " não encontrado.");
        }
        checklistRepository.deleteById(id);
    }
}
