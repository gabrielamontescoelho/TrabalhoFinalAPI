package br.com.serratec.trabalhofinalapi.dto;

import br.com.serratec.trabalhofinalapi.model.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequestDto(
        @NotBlank(message = "Insira o nome")
        String nome,

        @NotBlank(message = "Insira o Email")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "Insira o telefone")
        String telefone,

        @NotBlank(message = "Insira o CPF")
        @Size(min=11,max=11, message = "CPF deve conter 11 números")
        String cpf,

        @NotBlank(message = "Insira o Cep")
        String cep

) {

    public ClienteRequestDto(Cliente cliente) {
        this(
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getCpf(),
                cliente.getEndereco().getCep());
    }

}
