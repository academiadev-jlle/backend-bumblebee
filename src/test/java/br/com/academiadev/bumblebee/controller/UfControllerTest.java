//package br.com.academiadev.bumblebee.controller;
//
//import br.com.academiadev.bumblebee.model.Uf;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.hamcrest.core.Is.is;
//
//
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UfControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private UfController ufController;
//
//    @Test
//    public void postUf() throws Exception {
//        Uf uf = criaUf();
//        mvc.perform(get("/uf")).andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//    }
//
//    @Test
//    public void getUfPorId() throws Exception {
//        Uf uf = criaUf();
//        mvc.perform(get("/uf/"+uf.getIdUf())).andExpect(status().isOk())
//                .andExpect(jsonPath("$.idUf", is(new Long(uf.getIdUf()))));
//    }
//
//    @Test
//    public void deleteUfPorId() throws Exception {
//        Uf uf = criaUf();
//        mvc.perform(delete("/uf/"+uf.getIdUf()).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(jsonPath("$.excluido", is(Boolean.TRUE)));
//    }
//
//    private Uf criaUf(){
//        Uf uf = new Uf();
//        uf.setNome("Santa Catarina");
//        uf.setUf("SC");
//        uf.setExcluido(Boolean.FALSE);
//        ufController.criar(uf);
//        return uf;
//    }
//
//}
