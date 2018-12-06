package br.com.academiadev.bumblebee.service;

import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService extends ServiceAbstrataImpl<UsuarioRepository, Usuario, Long> {

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }

    public Usuario findByEmail(String email) {
        return getRepository().findByEmail(email);
    }

    public Optional<Usuario> findUserByResetToken(String resetToken) {
        return repository.findByResetToken(resetToken);
    }

    public Optional<Usuario> findUserByConfirmToken(String token) {
        return repository.findByConfirmToken(token);
    }
}

