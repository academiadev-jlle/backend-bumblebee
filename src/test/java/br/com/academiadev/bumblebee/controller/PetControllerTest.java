package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Comentario.ComentarioDTO;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOResponse;
import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import org.json.JSONObject;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .andExpect(jsonPath("$.usuario.nome", is("Administrador do sistema")))
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
    public void buscarPorUsuario() throws Exception {

        PetDTOResponse pet = getPet();

        String testeBusca = mvc.perform(get("/pet/{id}", pet.getId()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(testeBusca);
        JSONObject jsonUsuario = new JSONObject(json.get("usuario").toString());

        mvc.perform(get("/pet/usuario/{usuario}", jsonUsuario.get("id"))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.content[0].usuario.nome", is("Administrador do sistema")))
                .andExpect(jsonPath("$.content[0].usuario.id", is(jsonUsuario.get("id"))));

    }

    @Test
    public void buscaTodosPets() throws Exception {

        mvc.perform(get("/pet/pets")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void atualizaPet()throws Exception{

        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setLogradouro("Capinzal");
        localizacaoDTO.setReferencia("Localizacao com referencia editada");
        localizacaoDTO.setUf("SC");
        localizacaoDTO.setBairro("Floresta");
        localizacaoDTO.setCidade("Joinville");
        localizacaoDTO.setCep("89211-580");

        PetDTO petDTO = new PetDTO();
        petDTO.setCategoria(Categoria.ADOCAO);
        petDTO.setDescricao("descricao do pet editado");
        petDTO.setEspecie(Especie.CACHORRO);
        petDTO.setNome("nome do pet editado");
        petDTO.setPorte(Porte.PEQUENO);
        petDTO.setSexo("macho");
        petDTO.setLocalizacao(localizacaoDTO);

        String retonoPetEdiado = mvc.perform(post("/pet/atualizar/{pet}", getPet().getId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(petDTO)))
                .andExpect(jsonPath("$.descricao", is("descricao do pet editado")))
                .andExpect(jsonPath("$.localizacao.referencia", is("Localizacao com referencia editada")))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(retonoPetEdiado);

        mvc.perform(get("/pet/{id}", json.get("id"))
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.nome", is("nome do pet editado")))
                .andExpect(jsonPath("$.descricao", is("descricao do pet editado")))
                .andExpect(jsonPath("$.usuario.nome", is("Administrador do sistema")))
                .andExpect(jsonPath("$.localizacao.referencia", is("Localizacao com referencia editada")));
    }

}
