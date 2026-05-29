package br.com.serratec.trabalhofinalapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.trabalhofinalapi.dto.PecaRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.PecaResponseDTO;
import br.com.serratec.trabalhofinalapi.model.FotoPeca;
import br.com.serratec.trabalhofinalapi.model.Peca;
import br.com.serratec.trabalhofinalapi.service.FotoPecaService;
import br.com.serratec.trabalhofinalapi.service.PecaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pecas")
public class PecaController {

    @Autowired
    private PecaService service;

    @Autowired
    private FotoPecaService fotoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar peça", description = "Cadastra uma nova peça no estoque")
    public PecaResponseDTO inserir(@Valid @RequestBody PecaRequestDTO dto) {
        return service.inserir(dto);
    }

    @GetMapping
    @Operation(summary = "Listar peças com paginação", description = "Retorna uma lista paginada de peças cadastradas")
    public Page<PecaResponseDTO> listar(@PageableDefault(size = 5, page = 0, sort = "nome") Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar peça por ID", description = "Retorna os detalhes de uma peça específica")
    public PecaResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar peça", description = "Atualiza os dados de uma peça existente")
    public PecaResponseDTO editar(@PathVariable Long id, @Valid @RequestBody PecaRequestDTO dto) {
        return service.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir peça", description = "Remove uma peça do estoque")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PostMapping("/{id}/foto")
    @Operation(summary = "Cadastrar foto da peça", description = "Faz upload da foto de uma peça")
    public ResponseEntity<Void> inserirFoto(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        Peca peca = service.buscarEntidadePorId(id);
        fotoService.inserir(peca, file);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/foto")
    @Operation(summary = "Buscar foto da peça", description = "Retorna a foto cadastrada para uma peça")
    public ResponseEntity<ByteArrayResource> buscarFoto(@PathVariable Long id) {
        Peca peca = service.buscarEntidadePorId(id);
        FotoPeca foto = fotoService.buscarPorPeca(peca);

        ByteArrayResource resource = new ByteArrayResource(foto.getDados());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(foto.getTipo()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.inline().filename(foto.getNome()).build().toString())
                .body(resource);
    }
}