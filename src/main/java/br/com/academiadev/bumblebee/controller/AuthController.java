package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.mapper.UsuarioMapper;
import br.com.academiadev.bumblebee.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("oauth")
@Transactional
public class AuthController {

    private ConsumerTokenServices tokenServices;
    private UsuarioMapper usuarioMapper;

    @Autowired
    public AuthController(ConsumerTokenServices tokenServices,
                          UsuarioMapper usuarioMapper) {
        this.tokenServices = tokenServices;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping("info")
    public Object infoUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof String) {
            return authentication.getPrincipal();
        }

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return usuarioMapper.toDTOResponse(usuario);
    }

    @GetMapping("logout")
    public ResponseEntity<?> logout() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();

        if (details instanceof OAuth2AuthenticationDetails) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(tokenServices.revokeToken(((OAuth2AuthenticationDetails) details).getTokenValue()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ObjectNotFoundException("Nenhum usuário está logado no sistema"));
    }
}