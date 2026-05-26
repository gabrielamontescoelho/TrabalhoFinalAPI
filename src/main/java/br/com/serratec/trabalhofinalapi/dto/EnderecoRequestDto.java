package br.com.serratec.trabalhofinalapi.dto;

import br.com.serratec.trabalhofinalapi.model.Endereco;

public record EnderecoRequestDto(String cep, String logradouro, String bairro, String localidade, String uf) {

    public EnderecoRequestDto(Endereco endereco) {
        this(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getLocalidade(),
                endereco.getUf());
    }

}
