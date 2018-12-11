package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Comentario.ComentarioDTO;
import br.com.academiadev.bumblebee.dto.Comentario.ComentarioDTOResponse;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOResponse;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOResponse;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTO;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTOResponse;
import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import br.com.academiadev.bumblebee.mapper.LocalizacaoMapper;
import br.com.academiadev.bumblebee.mapper.UsuarioMapper;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.service.UsuarioService;
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
import java.util.Random;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AbstractControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LocalizacaoMapper localizacaoMapper;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Value("${security.oauth2.client.client-id}")
    private String client;

    @Value("${security.oauth2.client.client-secret}")
    private String secret;


    protected ComentarioDTOResponse getComentario() throws Exception {

        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setDescricao("Comentário do pet");

        String comentarioRetorno = mvc.perform(post("/comentario/pet/{pet}", getPet().getId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(comentarioDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Usuario usuario = usuarioService.findById(getUsuario().getId()).get();
        UsuarioDTOResponse usuarioDTOResponse = usuarioMapper.toDTOResponse(usuario);

        JSONObject json = new JSONObject(comentarioRetorno);
        ComentarioDTOResponse comentarioDTOResponse = new ComentarioDTOResponse();
        comentarioDTOResponse.setId(Long.valueOf((Integer) json.get("id")));
        comentarioDTOResponse.setDescricao((String) json.get("descricao"));
        comentarioDTOResponse.setUsuario(usuarioDTOResponse);
        return comentarioDTOResponse;
    }

    protected LocalizacaoDTOResponse getLocalizacao() throws Exception {


        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setLogradouro("Capinzal");
        localizacaoDTO.setReferencia("Casa com muro branco");
        localizacaoDTO.setUf("SC");
        localizacaoDTO.setBairro("Floresta");
        localizacaoDTO.setCidade("Joinville");
        localizacaoDTO.setCep("89211-580");

        String localizacaoRetorno = mvc.perform(post("/localizacao/")
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(localizacaoDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(localizacaoRetorno);

        LocalizacaoDTOResponse localizacaoDTOResponse = new LocalizacaoDTOResponse();
        localizacaoDTOResponse.setId(Long.valueOf((Integer) json.get("id")));
        localizacaoDTOResponse.setLogradouro(json.get("logradouro").toString());
        localizacaoDTOResponse.setReferencia(json.get("referencia").toString());
        localizacaoDTOResponse.setCidade(json.get("cidade").toString());
        localizacaoDTOResponse.setBairro(json.get("bairro").toString());
        localizacaoDTOResponse.setUf(json.get("uf").toString());
        localizacaoDTOResponse.setCep(json.get("cep").toString());

        return localizacaoDTOResponse;

    }

    protected UsuarioDTOResponse getUsuario() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail(getRandomEmail());
        usuarioDTO.setNome("José da Silva");
        usuarioDTO.setSenha("senha123");
        usuarioDTO.setContato("(47) 99999-9999");

        String usuarioRetorno = mvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(usuarioDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(usuarioRetorno);

        UsuarioDTOResponse usuarioDTOResponse = new UsuarioDTOResponse();
        usuarioDTOResponse.setId(Long.valueOf((Integer) json.get("id")));
        usuarioDTOResponse.setEmail((String) json.get("email"));
        usuarioDTOResponse.setNome((String) json.get("nome"));
        usuarioDTOResponse.setContato((String) json.get("contato"));

        return usuarioDTOResponse;
    }

    protected PetDTOResponse getPet() throws Exception {

        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setLogradouro("Capinzal");
        localizacaoDTO.setReferencia("Casa com muro branco");
        localizacaoDTO.setUf("SC");
        localizacaoDTO.setBairro("Floresta");
        localizacaoDTO.setCidade("Joinville");
        localizacaoDTO.setCep("89211-580");

        PetDTO petDTO = new PetDTO();
        petDTO.setCategoria(Categoria.ACHADOS);
        petDTO.setDescricao("Peludo e brincalhão");
        petDTO.setEspecie(Especie.CACHORRO);
        petDTO.setNome("Totó");
        petDTO.setPorte(Porte.PEQUENO);
        petDTO.setSexo("macho");
        petDTO.setLocalizacao(localizacaoDTO);

        String petRetorno = mvc.perform(post("/pet", getUsuario().getId())
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(petDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(petRetorno);

        Localizacao localizacao = localizacaoMapper.toEntity(localizacaoDTO);
        Usuario usuario = usuarioService.findById(getUsuario().getId()).get();

        PetDTOResponse petDTOResponse = new PetDTOResponse();
        petDTOResponse.setId(Long.valueOf((Integer) json.get("id")));
        petDTOResponse.setNome((String) json.get("nome"));
        petDTOResponse.setCategoria(petDTO.getCategoria());
        petDTOResponse.setDescricao((String) json.get("descricao"));
        petDTOResponse.setEspecie(petDTO.getEspecie());
        petDTOResponse.setPorte(petDTO.getPorte());
        petDTOResponse.setSexo((String) json.get("sexo"));

        LocalizacaoDTOResponse localizacaoDTOResponse = new LocalizacaoDTOResponse();

        JSONObject jsonLocalizacao = new JSONObject(json.get("localizacao").toString());

        localizacaoDTOResponse.setId(Long.valueOf((Integer) jsonLocalizacao.get("id")));
        localizacaoDTOResponse.setLogradouro((String) jsonLocalizacao.get("logradouro"));
        localizacaoDTOResponse.setReferencia((String) jsonLocalizacao.get("referencia"));
        localizacaoDTOResponse.setUf((String) jsonLocalizacao.get("uf"));
        localizacaoDTOResponse.setBairro((String) jsonLocalizacao.get("bairro"));
        localizacaoDTOResponse.setCidade(jsonLocalizacao.get("cidade").toString());
        localizacaoDTOResponse.setCep((String) jsonLocalizacao.get("cep"));

        petDTOResponse.setLocalizacao(localizacaoMapper.toDTOResponse(localizacao));
        petDTOResponse.setUsuario(usuarioMapper.toDTOResponse(usuario));

        return petDTOResponse;
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

    protected String getRandomEmail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString() + "@email.com";

    }


    protected static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }
}
