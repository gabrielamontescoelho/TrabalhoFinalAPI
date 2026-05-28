package br.com.serratec.trabalhofinalapi.dto;

public record LoginRequestDTO(
        String email,
        String senha
) {
}