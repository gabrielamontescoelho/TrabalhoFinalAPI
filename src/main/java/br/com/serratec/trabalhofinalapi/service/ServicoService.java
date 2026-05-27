package br.com.serratec.trabalhofinalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.model.Servico;
import br.com.serratec.trabalhofinalapi.repository.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public List<Servico> listar() {
        return repository.findAll();
    }

    public Servico buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Servico inserir(Servico servico) {
        return repository.save(servico);
    }

    public Servico editar(Long id, Servico servico) {

        Servico s = repository.findById(id).orElse(null);

        if(s != null) {

            s.setDescricao(servico.getDescricao());
            s.setValor(servico.getValor());
            s.setTempoEstimado(servico.getTempoEstimado());

            return repository.save(s);
        }

        return null;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}