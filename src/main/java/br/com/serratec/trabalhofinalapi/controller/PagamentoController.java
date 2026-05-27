package br.com.serratec.trabalhofinalapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.serratec.trabalhofinalapi.dto.PagamentoRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.PagamentoResponseDTO;
import br.com.serratec.trabalhofinalapi.dto.RelatorioFaturamentoDto;
import br.com.serratec.trabalhofinalapi.model.Pagamento;
import br.com.serratec.trabalhofinalapi.service.PagamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    @Autowired
    private PagamentoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagamentoResponseDTO inserir(@Valid @RequestBody PagamentoRequestDTO dto ){
        return service.inserir(dto);
    }

    @GetMapping
    public List<PagamentoResponseDTO> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Optional<Pagamento> buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PagamentoResponseDTO editar(@PathVariable Long id,@Valid @RequestBody PagamentoRequestDTO dto){
        return service.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable long id){
        service.deletar(id);
    }

    @GetMapping("/faturamento")
    public RelatorioFaturamentoDto gerarRelatorioFaturamento(){
        return service.gerarRelatorioFaturamento();
    }
}