package br.com.academiadev.bumblebee.service;

import br.com.academiadev.bumblebee.dto.Usuario.LoginSocialDTO;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Optional;

public interface FacebookService {

    String criarUrlAutorizacaoFacebook();

    Optional<OAuth2AccessToken> login(LoginSocialDTO dto);
}