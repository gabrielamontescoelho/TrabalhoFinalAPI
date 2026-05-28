package br.com.serratec.trabalhofinalapi.dto;

import br.com.serratec.trabalhofinalapi.model.ChecklistVistoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChecklistVistoriaRequestDTO(
        @NotBlank(message = "O nível de combustível é obrigatório (ex: Cheio, Meio, Reserva).")
        String nivelCombustivel,

        @NotNull(message = "Informe se o veículo possui estepe (true ou false).")
        Boolean temEstepe,

        @NotNull(message = "Informe se o veículo possui arranhões (true ou false).")
        Boolean temArranhoes,

        String observacoes,

        @NotNull(message = "O ID da Ordem de Serviço vinculada é obrigatório.")
        Long ordemServicoId
    ) {
        public ChecklistVistoriaRequestDTO(ChecklistVistoria checklistVistoria){
            this(
                checklistVistoria.getNivelCombustivel(),
                checklistVistoria.getTemEstepe(),
                checklistVistoria.getTemArranhoes(),
                checklistVistoria.getObservacoes(),
                checklistVistoria.getOrdemServico().getId()
            );
        }
}
