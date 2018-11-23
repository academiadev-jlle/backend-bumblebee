package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.model.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.io.IOException;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UsuarioController usuarioController;

    @Test
    public void postUsuario() throws Exception {
        Usuario usuario = criaUsuario();

        mvc.perform( post( "/usuario" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
                .content( convertObjectToJsonBytes( usuario ) ) )
                .andExpect( status().isOk() );

        mvc.perform( get( "/usuario" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$", Matchers.hasSize( 2 ) ) );
    }

    @Test
    public void buscaUsuarioPorId() throws Exception {
        Usuario usuario = criaUsuario();

        String usuarioRetorno = mvc.perform( post( "/usuario" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
                .content( convertObjectToJsonBytes( usuario ) ) )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer idUsuario = (Integer) new JSONObject(usuarioRetorno).get("id");

        mvc.perform( get( "/usuario/{id}", Long.valueOf(idUsuario))
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
                .andExpect( jsonPath( "$.nome", is( "José da Silva" ) ) )
                .andExpect( jsonPath( "$.email", is( "usuario@teste.com" ) ) );
    }


    @Test
    public void deleteUsuarioPorId() throws Exception {
        Usuario usuario = criaUsuario();

        String usuarioRetorno = mvc.perform( post( "/usuario" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
                .content( convertObjectToJsonBytes( usuario ) ) )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer idUsuario = (Integer) new JSONObject(usuarioRetorno).get("id");

        mvc.perform( delete( "/usuario/{id}", Long.valueOf(idUsuario))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    private Usuario criaUsuario(){
        Usuario usuario = new Usuario();
        usuario.setEmail("usuario@teste.com");
        usuario.setNome("José da Silva");
        usuario.setSenha("senha123");
        usuario.setExcluido(Boolean.FALSE);
        usuarioController.criar(usuario);
        return usuario;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion( JsonInclude.Include.NON_NULL );

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule( module );

        return mapper.writeValueAsBytes( object );
    }

}
