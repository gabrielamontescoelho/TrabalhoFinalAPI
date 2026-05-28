package br.com.serratec.trabalhofinalapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabalhofinalapi.dto.OrdemServicoRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.OrdemServicoResponseDTO;
import br.com.serratec.trabalhofinalapi.enums.StatusOrdemServico;
import br.com.serratec.trabalhofinalapi.service.OrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/ordens-servicos")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService service;

    @PostMapping
    @Operation(summary = "Criar uma nova ordem de serviço", description = "Cadastra uma nova ordem de serviço e seus itens no sistema")
    public ResponseEntity<OrdemServicoResponseDTO> inserir(@Valid @RequestBody OrdemServicoRequestDTO dto) {
        OrdemServicoResponseDTO response = service.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar ordem de serviço por ID", description = "Retorna os detalhes de uma ordem de serviço específica pelo seu ID")
    public ResponseEntity<OrdemServicoResponseDTO> buscarPorId(@PathVariable Long id) {
        OrdemServicoResponseDTO response = service.buscar(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Listar todas as ordens de serviço de forma paginada", description = "Retorna uma lista paginada de todas as ordens. Padrão: Listado por Id de forma crescente")
    public ResponseEntity<Page<OrdemServicoResponseDTO>> listarTodas(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<OrdemServicoResponseDTO> pagina = service.listarTodasPaginada(pageable);
        return ResponseEntity.ok(pagina);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar uma ordem de serviço", description = "Atualiza os dados de uma ordem de serviço existente pelo ID")
    public ResponseEntity<OrdemServicoResponseDTO> editar(@PathVariable Long id,
            @Valid @RequestBody OrdemServicoRequestDTO dto) {
        OrdemServicoResponseDTO response = service.editar(id, dto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status/{novoStatus}")
    @Operation(summary = "Alterar o status de uma ordem", description = "Atualiza parcialmente o status da ordem de serviço (ex: ABERTA, FINALIZADA, CANCELADA)")
    public ResponseEntity<OrdemServicoResponseDTO> alterarStatus(
            @PathVariable Long id,
            @PathVariable StatusOrdemServico novoStatus) {
        OrdemServicoResponseDTO response = service.alterarStatus(id, novoStatus);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma ordem de serviço", description = "Exclui permanentemente uma ordem de serviço do sistema pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
