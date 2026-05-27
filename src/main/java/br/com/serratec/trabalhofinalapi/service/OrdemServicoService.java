package br.com.serratec.trabalhofinalapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.dto.OrdemServicoRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.OrdemServicoResponseDTO;
import br.com.serratec.trabalhofinalapi.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
    @Autowired
    private OrdemServicoRepository repository;

    public OrdemServicoResponseDTO inserir(OrdemServicoRequestDTO dto){
        return null;
    }
}
