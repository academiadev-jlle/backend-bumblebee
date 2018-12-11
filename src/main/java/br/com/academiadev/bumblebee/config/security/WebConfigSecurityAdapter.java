package br.com.academiadev.bumblebee.config.security;

import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.transaction.Transactional;

@Configuration
@EnableWebSecurity
public class WebConfigSecurityAdapter extends WebSecurityConfigurerAdapter {

    @Bean
    private static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth, UsuarioRepository repository) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setSenha("adminadmin");
        usuario.setEmail("admin@admin.com");
        usuario.setNome("Administrador do sistema");
        usuario.setEnable(true);

        if (repository.count() == 0)
            repository.saveAndFlush(usuario);

        auth.userDetailsService(email -> repository.findByEmail(email).orElse(null));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET, //
                "/", //
                "/webjars/**", //
                "/*.html", //
                "/favicon.ico", //
                "/**/*.html", //
                "/v2/api-docs", //
                "/configuration/ui", //
                "/swagger-resources/**", //
                "/configuration/**", //
                "/swagger-ui.html", //
                "/webjars/**", //
                "/**/*.css", //
                "/**/*.js"//
        );

    }

    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }
}