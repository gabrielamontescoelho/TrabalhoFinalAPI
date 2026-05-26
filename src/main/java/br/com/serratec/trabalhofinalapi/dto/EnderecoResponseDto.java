package br.com.serratec.trabalhofinalapi.dto;

import br.com.serratec.trabalhofinalapi.model.Endereco;

public record EnderecoResponseDto(String cep, String logradouro, String bairro, String localidade, String uf) {

        public EnderecoResponseDto(Endereco endereco) {
        this(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getLocalidade(),
                endereco.getUf());
        }

}
