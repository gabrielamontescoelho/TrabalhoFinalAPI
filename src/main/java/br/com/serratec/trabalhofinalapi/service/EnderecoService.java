package br.com.serratec.trabalhofinalapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.trabalhofinalapi.dto.EnderecoResponseDto;
import br.com.serratec.trabalhofinalapi.exception.EnderecoException;
import br.com.serratec.trabalhofinalapi.model.Endereco;
import br.com.serratec.trabalhofinalapi.repository.EnderecoRepository;



@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public EnderecoResponseDto buscarCep(String cep) {
        Endereco enderecoBanco = repository.findByCep(cep);
        if (enderecoBanco != null) {
            return new EnderecoResponseDto(enderecoBanco);
        } else {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            Endereco enderecoViaCep = restTemplate.getForObject(url, Endereco.class);
            if (enderecoViaCep != null) {
                enderecoViaCep.setCep(enderecoViaCep.getCep().replaceAll("-", ""));
                return inserir(enderecoViaCep);
            }else{
                throw new EnderecoException("Cep não encontrado!");
            }
        }
    }

    private EnderecoResponseDto inserir(Endereco enderecoViaCep) {
        return new EnderecoResponseDto(repository.save(enderecoViaCep));
    }
}
