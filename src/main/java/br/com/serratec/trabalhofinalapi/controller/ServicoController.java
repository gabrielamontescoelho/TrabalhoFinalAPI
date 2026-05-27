package br.com.serratec.trabalhofinalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.serratec.trabalhofinalapi.model.Servico;
import br.com.serratec.trabalhofinalapi.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @GetMapping
    public List<Servico> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Servico buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Servico inserir(@RequestBody Servico servico) {
        return service.inserir(servico);
    }

    @PutMapping("/{id}")
    public Servico editar(
            @PathVariable Long id,
            @RequestBody Servico servico) {

        return service.editar(id, servico);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}