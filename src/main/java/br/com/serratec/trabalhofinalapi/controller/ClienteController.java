package br.com.serratec.trabalhofinalapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabalhofinalapi.dto.ClienteRequestDto;
import br.com.serratec.trabalhofinalapi.dto.ClienteResponseDto;
import br.com.serratec.trabalhofinalapi.model.Cliente;
import br.com.serratec.trabalhofinalapi.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes", description = "Endpoints para cadastro, edição, consulta e exclusão de clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @PostMapping
    @Operation(summary = "Cadastrar cliente", description = "Adiciona um novo cliente ao sistema")  
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto inserir(@Valid @RequestBody ClienteRequestDto dto){
        return service.inserir(dto);
    }

    @GetMapping
    @Operation(summary = "Listar clientes com paginação", description = "Retorna uma lista paginada de clientes")
    public Page<Cliente> listarPorPagina(
            @PageableDefault(size = 5, page = 0, sort= "nome") Pageable pageable) {
        return service.listarPorPagina(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Retorna os detalhes de um cliente específico pelo seu ID")
    public Optional<Cliente> buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar cliente", description = "Atualiza os dados de um cliente existente pelo seu ID")   
    public ClienteResponseDto editar(@PathVariable Long id,@Valid @RequestBody ClienteRequestDto dto){
        return service.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente", description = "Remove um cliente do sistema pelo seu ID")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }

}
