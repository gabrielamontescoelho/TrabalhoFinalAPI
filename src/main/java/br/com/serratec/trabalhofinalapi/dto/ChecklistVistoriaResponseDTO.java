package br.com.serratec.trabalhofinalapi.dto;

import br.com.serratec.trabalhofinalapi.model.ChecklistVistoria;

public record ChecklistVistoriaResponseDTO(Long id, String nivelCombustivel, Boolean temEstepe, Boolean temArranhoes,
    String observacoes, Long idOrdemSerico) {
        
        public ChecklistVistoriaResponseDTO(ChecklistVistoria checklist) {
        this(
            checklist.getId(),
            checklist.getNivelCombustivel(),
            checklist.getTemEstepe(),
            checklist.getTemArranhoes(),
            checklist.getObservacoes(),
            checklist.getOrdemServico() != null ? checklist.getOrdemServico().getId() : null
        );
    }
}
