package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.model.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UsuarioController usuarioController;

    @Test
    public void postUsuario() throws Exception {
        Usuario usuario = criaUsuario();
        mvc.perform(get("/usuario")).andExpect(status().isOk());
    }

    @Test
    public void getUsuarioPorId() throws Exception {
        Usuario usuario = criaUsuario();
        mvc.perform(get("/usuario/"+usuario.getIdUsuario())).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(usuario.getNome())));
    }

    @Test
    public void deleteUsuarioPorId() throws Exception {
        Usuario usuario = criaUsuario();
        mvc.perform(delete("/usuario/"+usuario.getIdUsuario()).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk());
    }

    private Usuario criaUsuario(){
        Usuario usuario = new Usuario();
        usuario.setEmail("usuario@bla.com");
        usuario.setNome("José da Silva");
        usuario.setSenha("senha123");
        usuario.setTelefone("(47) 992095717");
        usuario.setExcluido(Boolean.FALSE);
        usuarioController.criar(usuario);
        return usuario;
    }

}
