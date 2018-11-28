package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTO;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.model.Cidade;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Uf;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LocalizacaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void postLocalizacao() throws Exception {
        Uf uf = criaUf();

        Cidade cidade = criaCidade(uf);

        LocalizacaoDTO localizacaoDTO = criaLocalizacao(cidade);

        String localizacaoRetorno = mvc.perform( post( "/localizacao" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
                .content( convertObjectToJsonBytes( localizacaoDTO ) ) )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer idLocalizacao = (Integer) new JSONObject(localizacaoRetorno).get("id");

        mvc.perform( get( "/localizacao/{id}", Long.valueOf(idLocalizacao))
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
                .andExpect( jsonPath( "$.logradouro", is( "Capinzal" ) ) )
                .andExpect( jsonPath( "$.bairro", is( "Ubatuba" ) ) )
                .andExpect( jsonPath( "$.cidade.nome", is( "Joinville" ) ) )
                .andExpect( jsonPath( "$.cidade.uf.nome", is( "Santa Catarina" ) ) );
    }


    @Test
    public void deleteLocalizacaoPorId() throws Exception {
        Uf uf = criaUf();

        Cidade cidade = criaCidade(uf);

        LocalizacaoDTO localizacaoDTO = criaLocalizacao(cidade);

        String localizacaoRetorno = mvc.perform( post( "/localizacao" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
                .content( convertObjectToJsonBytes( localizacaoDTO ) ) )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer idLocalizacao = (Integer) new JSONObject(localizacaoRetorno).get("id");

        mvc.perform( delete( "/localizacao/{id}", Long.valueOf(idLocalizacao))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void buscaTodasLocalizacoes() throws Exception{
        mvc.perform( get( "/localizacao/localizacoes" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
                .andExpect( status().isOk() );
    }

    private LocalizacaoDTO criaLocalizacao(Cidade cidade){
        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setCidade(cidade);
        localizacaoDTO.setBairro("Ubatuba");
        localizacaoDTO.setLogradouro("Capinzal");
        localizacaoDTO.setReferencia("Casa com muro branco");
        return localizacaoDTO;
    }

    private Cidade criaCidade(Uf uf){
        Cidade cidade = new Cidade();
        cidade.setId(1L);
        cidade.setNome("Joinville");
        cidade.setUf(uf);
        return cidade;
    }

    private Uf criaUf(){
        Uf uf = new Uf();
        uf.setId(1L);
        uf.setNome("Santa Catarina");
        uf.setUf("SC");
        return uf;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion( JsonInclude.Include.NON_NULL );

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule( module );

        return mapper.writeValueAsBytes( object );
    }


}
