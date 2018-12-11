package br.com.academiadev.bumblebee.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**").authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuario", "/usuario/senha/**").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario/confirmar", "/pet", "/pet/**", "/foto", "/foto/**", "/usuario/senha/**", "/enum/**", "/localizacao/**", "/comentario/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
                // TODO: retirar linhas abaixo
//                .antMatchers(HttpMethod.POST, "/uf", "/cidade", "/bairro").permitAll()
                .antMatchers( HttpMethod.POST, "/**" ).permitAll()
                .antMatchers( HttpMethod.GET, "/**" ).permitAll()
                .antMatchers( HttpMethod.DELETE, "/**" ).permitAll()
                .anyRequest()
                .authenticated();

    }
}