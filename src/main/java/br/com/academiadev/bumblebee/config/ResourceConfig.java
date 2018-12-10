package br.com.academiadev.bumblebee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().logout().invalidateHttpSession(true)
                .and().antMatcher("/**").authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuario", "/usuario/senha/**").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario/confirmar", "/pet", "/pet/**", "/foto", "/foto/**", "/usuario/senha/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
                // TODO: retirar linhas abaixo
//                .antMatchers(HttpMethod.POST, "/uf", "/cidade", "/bairro").permitAll()
//                .antMatchers( HttpMethod.POST, "/**" ).permitAll()
//                .antMatchers( HttpMethod.GET, "/**" ).permitAll()
//                .antMatchers( HttpMethod.DELETE, "/**" ).permitAll()
                .anyRequest()
                .authenticated();

    }

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
                                                 OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("**"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}