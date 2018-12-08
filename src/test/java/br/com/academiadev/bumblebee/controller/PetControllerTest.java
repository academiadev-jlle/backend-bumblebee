
// todo: arrumar testes

package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Bairro.BairroDTOResponse;
import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTOResponse;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOResponse;
import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import java.io.StringReader;

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
                .andExpect(jsonPath("$.usuario.nome", is("José da Silva")))
                .andExpect(jsonPath("$.localizacao.cidade.nome", is("Joinville")));
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

        CidadeDTOResponse cidadeDTOResponse = getCidade();
        BairroDTOResponse bairroDTOResponse = getBairro();

        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setLogradouro("Capinzal");
        localizacaoDTO.setReferencia("Casa com muro branco");
        localizacaoDTO.setBairro(bairroDTOResponse);
        localizacaoDTO.setCidade(cidadeDTOResponse);

        PetDTO petDTO = new PetDTO();
        petDTO.setCategoria(Categoria.ADOCAO);
        petDTO.setDescricao("Peludo e brincalhão");
        petDTO.setEspecie(Especie.CACHORRO);
        petDTO.setNome("Totó");
        petDTO.setPorte(Porte.PEQUENO);
        petDTO.setSexo("macho");
        petDTO.setLocalizacao(localizacaoDTO);

        mvc.perform(post("/pet/usuario/{usuario}", getUsuario().getId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(petDTO)));

        mvc.perform(get("/pet/filtro")
                .param("categoria", Categoria.ADOCAO.getDescricao().toUpperCase())
                .param("especie", Especie.CACHORRO.getDescricao().toUpperCase())
                .param("porte", Porte.PEQUENO.getDescricao().toUpperCase())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.content[0].nome", is("Totó")))
                .andExpect(jsonPath("$.numberOfElements", is(1)));

    }

    @Test
    public void buscarPorUsuario() throws Exception {

        CidadeDTOResponse cidadeDTOResponse = getCidade();
        BairroDTOResponse bairroDTOResponse = getBairro();

        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setLogradouro("Capinzal");
        localizacaoDTO.setReferencia("Casa com muro branco");
        localizacaoDTO.setBairro(bairroDTOResponse);
        localizacaoDTO.setCidade(cidadeDTOResponse);

        PetDTO petDTO = new PetDTO();
        petDTO.setCategoria(Categoria.ADOCAO);
        petDTO.setDescricao("Peludo e brincalhão");
        petDTO.setEspecie(Especie.CACHORRO);
        petDTO.setNome("Totó");
        petDTO.setPorte(Porte.PEQUENO);
        petDTO.setSexo("macho");
        petDTO.setLocalizacao(localizacaoDTO);

        String retornoCadastroPet = mvc.perform(post("/pet/usuario/{usuario}", getUsuario().getId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(petDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(retornoCadastroPet);

        JSONObject jsonUsuario = new JSONObject(json.get("usuario").toString());

        mvc.perform(get("/pet/usuario/{usuario}", jsonUsuario.get("id"))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.content[0].usuario.nome", is("José da Silva")))
                .andExpect(jsonPath("$.content[0].nome", is("Totó")))
                .andExpect(status().isOk());

    }

    @Test
    public void buscaTodosPets() throws Exception {

        mvc.perform(get("/pet/pets")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

}
