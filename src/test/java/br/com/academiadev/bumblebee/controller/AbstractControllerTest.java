package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Bairro.BairroDTO;
import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTO;
import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTOResponse;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.dto.Uf.UfDTO;
import br.com.academiadev.bumblebee.dto.Uf.UfDTOResponse;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTO;
import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Value("${security.oauth2.client.client-id}")
    private String client;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;

    protected Long getUfId() throws Exception {
        UfDTO ufDTO = new UfDTO();
        ufDTO.setNome("Santa Catarina");
        ufDTO.setUf("SC");

        String uf = mvc.perform(post("/uf")
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(ufDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        return Long.valueOf((Integer) new JSONObject(uf).get("id"));
    }


    protected UfDTOResponse getUf() throws Exception {
        UfDTO ufDTO = new UfDTO();
        ufDTO.setNome("Santa Catarina");
        ufDTO.setUf("SC");

        String uf = mvc.perform(post("/uf")
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(ufDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();


        JSONObject json = new JSONObject(uf);
        UfDTOResponse ufDTOResponse = new UfDTOResponse();
        ufDTOResponse.setId(Long.valueOf((Integer) json.get("id")));
        ufDTOResponse.setNome((String) json.get("nome"));
        ufDTOResponse.setUf((String) json.get("uf"));


        return ufDTOResponse;
    }


    protected Long getCidadeId() throws Exception {

        CidadeDTO cidadeDTO = new CidadeDTO();
        cidadeDTO.setNome("Joinville");

        String cidadeRetorno = mvc.perform(post("/cidade/{uf}", getUfId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(cidadeDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        return Long.valueOf((Integer) new JSONObject(cidadeRetorno).get("id"));
    }

    protected CidadeDTOResponse getCidade() throws Exception {

        UfDTOResponse uf = getUf();

        CidadeDTO cidadeDTO = new CidadeDTO();
        cidadeDTO.setNome("Joinville");
        cidadeDTO.setUf(uf);

        String cidadeRetorno = mvc.perform(post("/cidade/")
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(cidadeDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(cidadeRetorno);

        CidadeDTOResponse cidadeDTOResponse = new CidadeDTOResponse();
        cidadeDTOResponse.setId(Long.valueOf((Integer) json.get("id")));
        cidadeDTOResponse.setNome((String) json.get("nome"));
        cidadeDTOResponse.setUf(uf);

        return cidadeDTOResponse;
    }

    protected Long getBairroId() throws Exception {

        BairroDTO bairroDTO = new BairroDTO();
        bairroDTO.setNome("Comasa");

        String bairroRetorno = mvc.perform(post("/bairro/cidade/{cidade}", getCidadeId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(bairroDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        return Long.valueOf((Integer) new JSONObject(bairroRetorno).get("id"));
    }

    protected Long getLocalizacaoId() throws Exception {
        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setLogradouro("Capinzal");
        localizacaoDTO.setReferencia("Casa com muro branco");

        String localizacaoRetorno = mvc.perform(post("/localizacao/cidade/{cidade}/bairro/{bairro}", getCidadeId(), getBairroId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(localizacaoDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        return Long.valueOf((Integer) new JSONObject(localizacaoRetorno).get("id"));

    }

    protected Long getUsuarioId() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("usuario@teste.com");
        usuarioDTO.setNome("José da Silva");
        usuarioDTO.setSenha("senha123");

        String usuarioRetorno = mvc.perform(post("/usuario")
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(usuarioDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        return Long.valueOf((Integer) new JSONObject(usuarioRetorno).get("id"));
    }

    protected Long getPetId() throws Exception {

        PetDTO petDTO = new PetDTO();
        petDTO.setCategoria(Categoria.ADOCAO);
        petDTO.setDescricao("Peludo e brincalhão");
        petDTO.setEspecie(Especie.CACHORRO);
        petDTO.setNome("Totó");
        petDTO.setPorte(Porte.PEQUENO);
        petDTO.setSexo("macho");

        String petRetorno = mvc.perform(post("/pet/usuario/{usuario}", getUsuarioId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(petDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        return Long.valueOf((Integer) new JSONObject(petRetorno).get("id"));
    }

    protected Long getPetUsuarioId() throws Exception {
        PetDTO petDTO = new PetDTO();
        petDTO.setCategoria(Categoria.ADOCAO);
        petDTO.setDescricao("Peludo e brincalhão");
        petDTO.setEspecie(Especie.CACHORRO);
        petDTO.setNome("Totó");
        petDTO.setPorte(Porte.PEQUENO);
        petDTO.setSexo("macho");

        Long usuario = getUsuarioId();

        String petRetorno = mvc.perform(post("/pet/usuario/{usuario}/localizacao/{localizacao}", usuario, getLocalizacaoId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(petDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        return usuario;
    }

    protected String getToken() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", "admin@admin.com");
        params.add("password", "adminadmin");

        String login = mvc.perform(
                post("/oauth/token")
                        .params(params)
                        .accept("application/json;charset=UTF-8")
                        .with(httpBasic(client, secret)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        return (String) new JSONObject(login).get("access_token");
    }


    protected static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }
}
