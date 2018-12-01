//package br.com.academiadev.bumblebee.controller;
//
//import br.com.academiadev.bumblebee.dto.Uf.UfDTO;
//import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTO;
//import br.com.academiadev.bumblebee.model.Uf;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.json.JSONObject;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import javax.transaction.Transactional;
//import java.io.IOException;
//
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.hamcrest.core.Is.is;
//
//
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class UfControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    public void postUf() throws Exception {
//        UfDTO ufDTO = criaUfDTO();
//
//        String usuarioRetorno = mvc.perform( post( "/uf" )
//                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
//                .content( convertObjectToJsonBytes( ufDTO ) ) )
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        Integer idUsuario = (Integer) new JSONObject(usuarioRetorno).get("id");
//
//        mvc.perform( get( "/uf/{id}", Long.valueOf(idUsuario))
//                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
//                .andExpect( jsonPath( "$.nome", is( "Santa Catarina" ) ) )
//                .andExpect( jsonPath( "$.uf", is( "SC" ) ) );
//    }
//
//
//    @Test
//    public void deleteUfPorId() throws Exception {
//        UfDTO ufDTO = criaUfDTO();
//
//        String usuarioRetorno = mvc.perform( post( "/uf" )
//                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
//                .content( convertObjectToJsonBytes( ufDTO ) ) )
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        Integer idUf = (Integer) new JSONObject(usuarioRetorno).get("id");
//
//        mvc.perform( delete( "/uf/{id}", Long.valueOf(idUf))
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void buscaTodasUfs() throws Exception{
//        mvc.perform( get( "/uf/ufs" )
//                .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE ) )
//                .andExpect( status().isOk() );
//    }
//
//    private UfDTO criaUfDTO(){
//        UfDTO ufDTO = new UfDTO();
//        ufDTO.setNome("Santa Catarina");
//        ufDTO.setUf("SC");
//        return ufDTO;
//    }
//
//    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setSerializationInclusion( JsonInclude.Include.NON_NULL );
//
//        JavaTimeModule module = new JavaTimeModule();
//        mapper.registerModule( module );
//
//        return mapper.writeValueAsBytes( object );
//    }
//
//}
