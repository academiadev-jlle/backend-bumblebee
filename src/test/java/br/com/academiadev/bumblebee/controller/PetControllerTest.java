package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import br.com.academiadev.bumblebee.model.Cidade;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Uf;
import br.com.academiadev.bumblebee.model.Usuario;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PetControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void postPet() throws Exception {

        PetDTO petDTO = criaPetDTO();

        String petRetorno = mvc.perform( post( "/pet/usuario/{usuario}/localizacao/{localizacao}", 64L, 1L )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
                .content( convertObjectToJsonBytes( petDTO ) ) )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer idPet = (Integer) new JSONObject(petRetorno).get("id");

        mvc.perform( get( "/pet/{id}", Long.valueOf(idPet))
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
                .andExpect( jsonPath( "$.nome", is( "Totó" ) ) )
                .andExpect( jsonPath( "$.descricao", is( "Peludo e brincalhão" ) ) )
                .andExpect( jsonPath( "$.usuario.nome", is( "Bruno Muehlbauer de Souza" ) ) )
                .andExpect( jsonPath( "$.localizacao.cidade.nome", is( "Joinville" ) ) );
    }


    @Test
    public void deletePetPorId() throws Exception {

        PetDTO petDTO = criaPetDTO();

        String petRetorno = mvc.perform( post( "/pet/usuario/{usuario}/localizacao/{localizacao}", 64L, 1L )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
                .content( convertObjectToJsonBytes( petDTO ) ) )
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer idPet = (Integer) new JSONObject(petRetorno).get("id");

        mvc.perform( delete( "/pet/{id}", Long.valueOf(idPet))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void buscarPorCategoria() throws Exception{
        mvc.perform( get( "/pet/categoria/ADOCAO" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
                .andExpect( jsonPath( "$[0].categoria", is( "ADOCAO" ) ) )
                .andExpect( status().isOk() );
    }

    @Test
    public void buscarPorUsuario() throws Exception{
        mvc.perform( get( "/pet/usuario/64" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
                .andExpect( jsonPath( "$[0].usuario.nome", is( "Bruno Muehlbauer de Souza" ) ) )
                .andExpect( status().isOk() );
    }

    @Test
    public void buscaTodosPets() throws Exception{
        mvc.perform( get( "/pet/pets" )
                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
                .andExpect( status().isOk() );
    }

    private PetDTO criaPetDTO(){
        PetDTO petDTO = new PetDTO();
        petDTO.setCategoria(Categoria.ADOCAO);
        petDTO.setDescricao("Peludo e brincalhão");
        petDTO.setEspecie(Especie.CACHORRO);
        petDTO.setNome("Totó");
        petDTO.setPorte(Porte.PEQUENO);
        petDTO.setSexo("macho");
        return petDTO;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion( JsonInclude.Include.NON_NULL );

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule( module );

        return mapper.writeValueAsBytes( object );
    }

}
