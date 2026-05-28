package br.com.serratec.trabalhofinalapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.trabalhofinalapi.exception.RegraNegocioException;
import br.com.serratec.trabalhofinalapi.model.PerfilUsuario;
import br.com.serratec.trabalhofinalapi.model.Usuario;
import br.com.serratec.trabalhofinalapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RegraNegocioException("E-mail já cadastrado");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        if (usuario.getPerfil() == null) {
            usuario.setPerfil(PerfilUsuario.ROLE_CLIENTE);
        }

        return usuarioRepository.save(usuario);
    }
}