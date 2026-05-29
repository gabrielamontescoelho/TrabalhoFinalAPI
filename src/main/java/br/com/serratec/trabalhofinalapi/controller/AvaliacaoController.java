package br.com.serratec.trabalhofinalapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabalhofinalapi.dto.AvaliacaoRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.AvaliacaoResponseDTO;
import br.com.serratec.trabalhofinalapi.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> cadastrar(@RequestBody AvaliacaoRequestDTO dto) {

        AvaliacaoResponseDTO response = service.cadastrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}