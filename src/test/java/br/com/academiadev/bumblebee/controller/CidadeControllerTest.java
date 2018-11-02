package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.model.Cidade;
import br.com.academiadev.bumblebee.model.Uf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class CidadeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CidadeController cidadeController;

    @Autowired
    private UfController ufController;

    @Test
    public void postCidade() throws Exception {
        Cidade cidade = criaCidade();
        mvc.perform(get("/cidade")).andExpect(status().isOk());
    }

    @Test
    public void getCidadePorId() throws Exception {
        Cidade cidade = criaCidade();
        mvc.perform(get("/cidade/"+cidade.getIdCidade())).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(cidade.getNome())));
    }

    @Test
    public void deleteCidadePorId() throws Exception {
        Cidade cidade = criaCidade();
        mvc.perform(delete("/cidade/"+cidade.getIdCidade()).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(jsonPath("$.excluido", is(Boolean.TRUE)));
    }

    private Cidade criaCidade(){
        Uf uf = new Uf();
        uf.setNome("Santa Catarina");
        uf.setUf("SC");
        uf.setExcluido(Boolean.FALSE);
        ufController.criar(uf);

        Cidade cidade = new Cidade();
        cidade.setNome("Joinville Teste");
        cidade.setUf(uf);
        cidade.setExcluido(Boolean.FALSE);
        cidadeController.criar(cidade);
        return cidade;
    }

}
