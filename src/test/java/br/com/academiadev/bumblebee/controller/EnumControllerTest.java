package br.com.academiadev.bumblebee.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EnumControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getCategorias() throws Exception {
        mvc.perform(get("/enum/categorias")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getEspecies() throws Exception {
        mvc.perform(get("/enum/especies")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getPortes() throws Exception {
        mvc.perform(get("/enum/portes")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }
}