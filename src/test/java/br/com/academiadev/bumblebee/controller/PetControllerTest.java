package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PetControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void postPet() throws Exception {

        mvc.perform(get("/pet/{id}", getPet().getId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.nome", is("Totó")))
                .andExpect(jsonPath("$.descricao", is("Peludo e brincalhão")))
                .andExpect(jsonPath("$.usuario.nome", is("José da Silva")))
                .andExpect(jsonPath("$.localizacao.cidade", is("Joinville")));
    }

    @Test
    public void deletePetPorId() throws Exception {
        mvc.perform(delete("/pet/{id}", getPet().getId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void buscarPorFiltro() throws Exception {

        getPet();

        mvc.perform(get("/pet/filtro")
                .param("categoria", Categoria.ADOCAO.getDescricao().toUpperCase())
                .param("especie", Especie.CACHORRO.getDescricao().toUpperCase())
                .param("porte", Porte.PEQUENO.getDescricao().toUpperCase())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.content[0].nome", is("Totó")))
                .andExpect(jsonPath("$.numberOfElements", is(1)));

    }

//    @Test
//    public void buscarPorUsuario() throws Exception {
//
//        PetDTOResponse pet = getPet();
//
//        mvc.perform(get("/pet/usuario/{usuario}", pet.getUsuario().getId())
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$.content[0].usuario.nome", is("José da Silva")))
//                .andExpect(jsonPath("$.content[0].nome", is("Totó")))
//                .andExpect(status().isOk());
//
//    }

    @Test
    public void buscaTodosPets() throws Exception {

        mvc.perform(get("/pet/pets")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

}
