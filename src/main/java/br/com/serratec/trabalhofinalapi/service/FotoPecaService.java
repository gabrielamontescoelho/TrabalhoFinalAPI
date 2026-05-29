package br.com.serratec.trabalhofinalapi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.trabalhofinalapi.exception.RegraNegocioException;
import br.com.serratec.trabalhofinalapi.exception.ResourceNotFoundException;
import br.com.serratec.trabalhofinalapi.model.FotoPeca;
import br.com.serratec.trabalhofinalapi.model.Peca;
import br.com.serratec.trabalhofinalapi.repository.FotoPecaRepository;

@Service
public class FotoPecaService {

    @Autowired
    private FotoPecaRepository repository;

    public FotoPeca inserir(Peca peca, MultipartFile file) {
        try {
            FotoPeca foto = new FotoPeca();
            foto.setDados(file.getBytes());
            foto.setTipo(file.getContentType());
            foto.setNome(file.getOriginalFilename());
            foto.setPeca(peca);

            return repository.save(foto);
        } catch (IOException e) {
            throw new RegraNegocioException("Erro ao processar a foto da peça");
        }
    }

    public FotoPeca buscarPorPeca(Peca peca) {
        return repository.findByPeca(peca)
                .orElseThrow(() -> new ResourceNotFoundException("Foto da peça não encontrada"));
    }
}