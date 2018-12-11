package br.com.academiadev.bumblebee.service.impl;

import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.service.FacebookService;
import br.com.academiadev.bumblebee.dto.Usuario.LoginSocialDTO;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.HashMap;
import java.util.Optional;

@Service
public class FacebookServiceImpl implements FacebookService {

    @Value("${social.facebook.appId}")
    private String facebookAppId;

    @Value("${social.facebook.appSecret}")
    private String facebookAppSecret;

    @Value("${social.facebook.redirectUri}")
    private String redirectUri;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    private UsuarioRepository usuarioRepository;
    private final TokenEndpoint tokenEndpoint;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public FacebookServiceImpl(UsuarioRepository usuarioRepository, TokenEndpoint tokenEndpoint) {
        this.usuarioRepository = usuarioRepository;
        this.tokenEndpoint = tokenEndpoint;
    }

    @Override
    public String criarUrlAutorizacaoFacebook() {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookAppSecret);

        OAuth2Operations operacoes = connectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();

        params.setRedirectUri(redirectUri);
        params.setScope("public_profile,email");

        return operacoes.buildAuthorizeUrl(params);
    }

    @Override
    public Optional<OAuth2AccessToken> login(LoginSocialDTO dto) {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookAppSecret);
        AccessGrant grant = connectionFactory.getOAuthOperations().exchangeForAccess(dto.getCode(), dto.getRedirectUri(), null);

        String token = grant.getAccessToken();

        if (token.isEmpty()) {
            return Optional.empty();
        }

        return getUsuarioFacebook(token);
    }

    private Optional<OAuth2AccessToken> getUsuarioFacebook(String token) {
        Facebook facebook = new FacebookTemplate(token);

        String[] campos = {
                "id",
                "name",
                "picture",
                "short_name",
                "email"
        };

        User usuarioFacebook = Optional.ofNullable(facebook.fetchObject("me", User.class, campos)).orElse(null);

        if (usuarioFacebook == null) {
            return Optional.empty();
        }

        String access = usuarioFacebook.getEmail() == null ? usuarioFacebook.getId() : usuarioFacebook.getEmail();
        if (!usuarioRepository.findByEmail(access).isPresent()) {
            usuarioRepository.saveAndFlush(Usuario.builder()
                    .email(access)
                    .nome(usuarioFacebook.getName())
                    .senha(passwordEncoder.encode(usuarioFacebook.getId()))
                    .enable(true).build());
        }

        UsernamePasswordAuthenticationToken principal = new UsernamePasswordAuthenticationToken(clientId, null, null);
        SecurityContextHolder.getContext().setAuthentication(principal);

        HashMap<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("username", access);
        params.put("password", usuarioFacebook.getId());

        try {
            return Optional.ofNullable(tokenEndpoint.getAccessToken(principal, params).getBody());
        } catch (HttpRequestMethodNotSupportedException e) {
            return Optional.empty();
        } catch (InvalidGrantException e) {
            throw new ObjectNotFoundException("Este endereço de e-mail já está cadastrado.");
        }
    }

}