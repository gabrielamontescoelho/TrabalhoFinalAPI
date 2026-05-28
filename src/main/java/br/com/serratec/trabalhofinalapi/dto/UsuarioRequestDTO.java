package br.com.serratec.trabalhofinalapi.dto;

import br.com.serratec.trabalhofinalapi.model.PerfilUsuario;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha,
        PerfilUsuario perfil
) {
}