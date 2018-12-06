//package br.com.academiadev.bumblebee.controller;
//
//import br.com.academiadev.bumblebee.dto.Comentario.ComentarioDTO;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import javax.transaction.Transactional;
//
//import static org.hamcrest.core.Is.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class ComentarioControllerTest extends AbstractControllerTest{
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Value("${security.oauth2.client.client-id}")
//    private String client;
//
//    @Value("${security.oauth2.client.client-secret}")
//    private String secret;
//
//    @Test
//    public void postComentario() throws Exception {
//        mvc.perform(get("/comentario/{id}", getComentarioId())
//                .header("Authorization", "Bearer " + getToken())
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$.descricao", is("Comentário do pet")));
//    }
//
//    @Test
//    public void deletecomentarioPorId() throws Exception {
//
//        mvc.perform(delete("/comentario/{id}", getComentarioId())
//                .header("Authorization", "Bearer " + getToken())
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void buscaTodosComentarios() throws Exception {
//        mvc.perform(get("/comentario/comentarios")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void buscaComentariosPorPet() throws Exception {
//        mvc.perform(get("/comentario/pet/{pet}", 1L)
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void atualizaComentario()throws Exception{
//        ComentarioDTO comentarioDTO = new ComentarioDTO();
//        comentarioDTO.setDescricao("Comentário do pet editado");
//
//        mvc.perform(post("/comentario/atualizar/{id}", getComentarioId())
//                .header("Authorization", "Bearer " + getToken())
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(convertObjectToJsonBytes(comentarioDTO)))
//                .andExpect(jsonPath("$.descricao", is("Comentário do pet editado")));
//    }
//
//}
