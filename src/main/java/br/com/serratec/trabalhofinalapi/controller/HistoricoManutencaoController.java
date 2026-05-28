package br.com.serratec.trabalhofinalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabalhofinalapi.dto.HistoricoRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.RelatorioOficinaDTO;
import br.com.serratec.trabalhofinalapi.model.HistoricoManutencao;
import br.com.serratec.trabalhofinalapi.service.HistoricoManutencaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Histórico de Manutenção", description = "Endpoints para histórico de serviços e relatórios da oficina")
@RestController
@RequestMapping("/historicos")
public class HistoricoManutencaoController {

    @Autowired
    private HistoricoManutencaoService historicoService;

    @GetMapping
    @Operation(summary = "Listar histórico de manutenção", description = "Retorna uma lista de todos os registros de manutenção")
    public ResponseEntity<List<HistoricoManutencao>> listarTodos() {
        return ResponseEntity.ok(historicoService.listarTodos());
    }

    @PostMapping
    @Operation(summary = "Cadastrar histórico de manutenção", description = "Adiciona um novo registro de manutenção para um veículo")
    public ResponseEntity<HistoricoManutencao> cadastrar(@RequestBody HistoricoRequestDTO dto) {
        HistoricoManutencao novo = historicoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar histórico de manutenção", description = "Atualiza um registro de manutenção existente pelo seu ID")
    public ResponseEntity<HistoricoManutencao> atualizar(@PathVariable Long id, @RequestBody HistoricoRequestDTO dto) {
        return ResponseEntity.ok(historicoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir histórico de manutenção", description = "Remove um registro de manutenção do sistema pelo seu ID")    
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        historicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/relatorio/{veiculoId}")
    @Operation(summary = "Obter relatório de manutenção", description = "Retorna um relatório com os dados de manutenção para um veículo específico")
    public ResponseEntity<RelatorioOficinaDTO> obterRelatorio(@PathVariable Long veiculoId) {
        RelatorioOficinaDTO relatorio = historicoService.obterRelatorioDados(veiculoId);
        return ResponseEntity.ok(relatorio);
    }
}

