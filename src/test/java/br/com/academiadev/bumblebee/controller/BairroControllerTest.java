package br.com.academiadev.bumblebee.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class BairroControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Value("${security.oauth2.client.client-id}")
    private String client;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;

    @Test
    public void postBairro() throws Exception {
        mvc.perform(get("/bairro/{id}", getBairroId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.nome", is("Comasa")));
    }

    @Test
    public void deleteBairroPorId() throws Exception {

        mvc.perform(delete("/bairro/{id}", getBairroId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void buscaTodaosBairros() throws Exception {
        mvc.perform(get("/bairro/bairros")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }
}
