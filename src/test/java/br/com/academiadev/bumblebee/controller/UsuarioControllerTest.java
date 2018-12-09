package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Comentario.ComentarioDTO;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTO;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTOResponse;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UsuarioControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void postUsuario() throws Exception {

        UsuarioDTOResponse usuario = getUsuario();

        mvc.perform(get("/usuario/{id}", usuario.getId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.nome", is(usuario.getNome())))
                .andExpect(jsonPath("$.email", is(usuario.getEmail())));
    }

    @Test
    public void deleteUsuarioPorId() throws Exception {
        mvc.perform(delete("/usuario/{id}", getUsuario().getId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void buscaTodosUsuario() throws Exception {
        mvc.perform(get("/usuario/usuarios")
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void atualizaUsuario()throws Exception{

        UsuarioDTOResponse usuarioDTOResponse = new UsuarioDTOResponse();
        usuarioDTOResponse.setId(getUsuario().getId());
        usuarioDTOResponse.setEmail("meu email editado");
        usuarioDTOResponse.setNome("José da Silva com nome editado");
        usuarioDTOResponse.setContato("(47) 99999-9999");

        String retornoUsuario = mvc.perform(post("/usuario/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(usuarioDTOResponse)))
                .andExpect(jsonPath("$.nome", is("José da Silva com nome editado")))
                .andExpect(jsonPath("$.email", is("meu email editado"))).andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(retornoUsuario);

        mvc.perform(get("/usuario/{id}", json.get("id"))
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.nome", is("José da Silva com nome editado")))
                .andExpect(jsonPath("$.email", is("meu email editado")));
    }


}
