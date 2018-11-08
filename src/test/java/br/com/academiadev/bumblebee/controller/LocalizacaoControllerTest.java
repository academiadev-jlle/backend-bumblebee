//package br.com.academiadev.bumblebee.controller;
//
//import br.com.academiadev.bumblebee.model.Cidade;
//import br.com.academiadev.bumblebee.model.Localizacao;
//import br.com.academiadev.bumblebee.model.Uf;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.core.Is.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class LocalizacaoControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private CidadeController cidadeController;
//
//    @Autowired
//    private UfController ufController;
//
//    @Autowired
//    private LocalizacaoController localizacaoController;
//
//    @Test
//    public void postLocalizacao() throws Exception {
//        Localizacao localizacao = criaLocalizacao();
//        mvc.perform(get("/localizacao")).andExpect(status().isOk());
//    }
//
//    @Test
//    public void getLocalizacaoPorId() throws Exception {
//        Localizacao localizacao = criaLocalizacao();
//        mvc.perform(get("/localizacao/"+localizacao.getIdLocalizacao())).andExpect(status().isOk())
//                .andExpect(jsonPath("$.bairro", is(localizacao.getBairro())));
//    }
//
//    @Test
//    public void deleteLocalizacaoPorId() throws Exception {
//        Localizacao localizacao = criaLocalizacao();
//        mvc.perform(delete("/localizacao/"+localizacao.getIdLocalizacao()).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(jsonPath("$.excluido", is(Boolean.TRUE)));
//    }
//
//    private Localizacao criaLocalizacao(){
//        Uf uf = new Uf();
//        uf.setNome("Santa Catarina");
//        uf.setUf("SC");
//        uf.setExcluido(Boolean.FALSE);
//        ufController.criar(uf);
//
//        Cidade cidade = new Cidade();
//        cidade.setNome("Joinville Teste");
//        cidade.setUf(uf);
//        cidade.setExcluido(Boolean.FALSE);
//        cidadeController.criar(cidade);
//
//        Localizacao localizacao = new Localizacao();
//        localizacao.setBairro("Bucarein");
//        localizacao.setLogradouro("Padre Kolb");
//        localizacao.setReferencia("Campo Belo");
//        localizacao.setCidade(cidade);
//        localizacao.setExcluido(Boolean.FALSE);
//        localizacaoController.criar(localizacao);
//        return localizacao;
//    }
//
//}
