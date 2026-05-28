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

import br.com.serratec.trabalhofinalapi.dto.ChecklistVistoriaRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.ChecklistVistoriaResponseDTO;
import br.com.serratec.trabalhofinalapi.service.ChecklistVistoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vistorias")
public class ChecklistVistoriaController {

    @Autowired
    private ChecklistVistoriaService checklistService;

    @PostMapping
    public ResponseEntity<ChecklistVistoriaResponseDTO> inserir(@Valid @RequestBody ChecklistVistoriaRequestDTO dto) {
        ChecklistVistoriaResponseDTO response = checklistService.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ChecklistVistoriaResponseDTO>> listarTodos() {
        List<ChecklistVistoriaResponseDTO> lista = checklistService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChecklistVistoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        ChecklistVistoriaResponseDTO response = checklistService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChecklistVistoriaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ChecklistVistoriaRequestDTO dto) {
        ChecklistVistoriaResponseDTO response = checklistService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        checklistService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
