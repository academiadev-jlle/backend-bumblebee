package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTO;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTOResponse;
import br.com.academiadev.bumblebee.mapper.UsuarioMapper;
import br.com.academiadev.bumblebee.model.Usuario;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UsuarioControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Test
    public void postUsuario() throws Exception {
        mvc.perform(get("/usuario/{id}", getUsuario().getId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.nome", is("José da Silva")))
                .andExpect(jsonPath("$.email", is("usuario@teste.com")));
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


}
