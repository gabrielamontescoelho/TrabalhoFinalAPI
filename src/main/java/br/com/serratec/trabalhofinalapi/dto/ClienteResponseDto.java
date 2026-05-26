package br.com.serratec.trabalhofinalapi.dto;

import br.com.serratec.trabalhofinalapi.model.Cliente;

public record ClienteResponseDto( Long id,String nome,String email,String telefone,String cpf, EnderecoResponseDto endereco) {

    public ClienteResponseDto(Cliente cliente) {
        this(
            cliente.getId(),
            cliente.getNome(),
            cliente.getEmail(),
            cliente.getTelefone(),
            cliente.getCpf(),
            new EnderecoResponseDto(cliente.getEndereco()));
    }

}
