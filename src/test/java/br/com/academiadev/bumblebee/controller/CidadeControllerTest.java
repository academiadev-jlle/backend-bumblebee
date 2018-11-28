package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTO;
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
public class CidadeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void postCidade() throws Exception {
        Uf uf = criaUf();

        CidadeDTO cidadeDTO = criaCidadeDTO(uf);

        String cidadeRetorno = mvc.perform( post( "/cidade" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
                .content( convertObjectToJsonBytes( cidadeDTO ) ) )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer idCidade = (Integer) new JSONObject(cidadeRetorno).get("id");

        mvc.perform( get( "/cidade/{id}", Long.valueOf(idCidade))
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
                .andExpect( jsonPath( "$.nome", is( "Joinville" ) ) )
                .andExpect( jsonPath( "$.uf.nome", is( "Santa Catarina" ) ) );
    }


    @Test
    public void deleteCidadePorId() throws Exception {
        Uf uf = criaUf();

        CidadeDTO cidadeDTO = criaCidadeDTO(uf);

        String cidadeRetorno = mvc.perform( post( "/cidade" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
                .content( convertObjectToJsonBytes( cidadeDTO ) ) )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer idCidade = (Integer) new JSONObject(cidadeRetorno).get("id");

        mvc.perform( delete( "/cidade/{id}", Long.valueOf(idCidade))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

//    @Test
//    public void buscaTodasCidades() throws Exception{
//        mvc.perform( get( "/cidade/cidades" )
//                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
//                .andExpect( status().isOk() );
//    }

    private CidadeDTO criaCidadeDTO(Uf uf){
        CidadeDTO cidadeDTO = new CidadeDTO();
        cidadeDTO.setNome("Joinville");
        cidadeDTO.setUf(uf);
        return cidadeDTO;
    }

    private Uf criaUf(){
        Uf uf = new Uf();
        uf.setId(4L);
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
