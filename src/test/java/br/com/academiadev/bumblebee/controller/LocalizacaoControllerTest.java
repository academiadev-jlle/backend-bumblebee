package br.com.academiadev.bumblebee.controller;

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
public class LocalizacaoControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void postLocalizacao() throws Exception {

        mvc.perform(get("/localizacao/{id}", getLocalizacao().getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.logradouro", is("Capinzal")))
                .andExpect(jsonPath("$.bairro.nome", is("Comasa")))
                .andExpect(jsonPath("$.cidade.nome", is("Joinville")))
                .andExpect(jsonPath("$.bairro.nome", is("Comasa")))
                .andExpect(jsonPath("$.cidade.uf.nome", is("Santa Catarina")));

    }


    @Test
    public void deleteLocalizacaoPorId() throws Exception {

        mvc.perform(delete("/localizacao/{id}", getLocalizacao().getId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void buscaTodasLocalizacoes() throws Exception {
        mvc.perform(get("/localizacao/localizacoes")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

}
