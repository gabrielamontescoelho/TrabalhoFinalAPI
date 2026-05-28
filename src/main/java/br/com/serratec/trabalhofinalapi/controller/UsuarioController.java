package br.com.serratec.trabalhofinalapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabalhofinalapi.dto.UsuarioRequestDTO;
import br.com.serratec.trabalhofinalapi.dto.UsuarioResponseDTO;
import br.com.serratec.trabalhofinalapi.model.Usuario;
import br.com.serratec.trabalhofinalapi.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO cadastrar(@RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setPerfil(dto.perfil());

        Usuario usuarioSalvo = usuarioService.cadastrar(usuario);

        return new UsuarioResponseDTO(usuarioSalvo);
    }
}