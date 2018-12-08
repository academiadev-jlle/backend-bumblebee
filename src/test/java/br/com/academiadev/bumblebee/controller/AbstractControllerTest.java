package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Bairro.BairroDTO;
import br.com.academiadev.bumblebee.dto.Bairro.BairroDTOResponse;
import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTO;
import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTOResponse;
import br.com.academiadev.bumblebee.dto.Comentario.ComentarioDTO;
import br.com.academiadev.bumblebee.dto.Comentario.ComentarioDTOResponse;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOResponse;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOResponse;
import br.com.academiadev.bumblebee.dto.Uf.UfDTO;
import br.com.academiadev.bumblebee.dto.Uf.UfDTOResponse;
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

    protected ComentarioDTOResponse getComentario() throws Exception {

        ComentarioDTO comentarioDTO = new ComentarioDTO();
        comentarioDTO.setDescricao("Comentário do pet");

        String comentarioRetorno = mvc.perform(post("/comentario/{pet}/{usuario}", getPet().getId(), getUsuario().getId())
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

    protected BairroDTOResponse getBairro() throws Exception {

        CidadeDTOResponse cidadeDTOResponse = getCidade();

        BairroDTO bairroDTO = new BairroDTO();
        bairroDTO.setNome("Comasa");
        bairroDTO.setCidade(cidadeDTOResponse);

        String bairroRetorno = mvc.perform(post("/bairro/")
                .header("Authorization", "Bearer " + getToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(convertObjectToJsonBytes(bairroDTO)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject json = new JSONObject(bairroRetorno);


        BairroDTOResponse bairroDTOResponse = new BairroDTOResponse();
        bairroDTOResponse.setId(Long.valueOf((Integer) json.get("id")));
        bairroDTOResponse.setNome((String) json.get("nome"));
        // todo: fazer do jeito certo
        bairroDTOResponse.setCidade(cidadeDTOResponse);


        return bairroDTOResponse;
    }

    protected LocalizacaoDTOResponse getLocalizacao() throws Exception {

        CidadeDTOResponse cidadeDTOResponse = getCidade();
        BairroDTOResponse bairroDTOResponse = getBairro();

        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setLogradouro("Capinzal");
        localizacaoDTO.setReferencia("Casa com muro branco");
        localizacaoDTO.setBairro(bairroDTOResponse);
        localizacaoDTO.setCidade(cidadeDTOResponse);

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
        localizacaoDTOResponse.setLogradouro((String) json.get("logradouro"));
        localizacaoDTOResponse.setReferencia((String) json.get("referencia"));
        localizacaoDTOResponse.setCidade(cidadeDTOResponse);
        localizacaoDTOResponse.setBairro(bairroDTOResponse);

        return localizacaoDTOResponse;

    }

    protected UsuarioDTOResponse getUsuario() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("usuario@teste.com");
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

        CidadeDTOResponse cidadeDTOResponse = getCidade();
        BairroDTOResponse bairroDTOResponse = getBairro();

        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();
        localizacaoDTO.setLogradouro("Capinzal");
        localizacaoDTO.setReferencia("Casa com muro branco");
        localizacaoDTO.setBairro(bairroDTOResponse);
        localizacaoDTO.setCidade(cidadeDTOResponse);


        PetDTO petDTO = new PetDTO();
        petDTO.setCategoria(Categoria.ADOCAO);
        petDTO.setDescricao("Peludo e brincalhão");
        petDTO.setEspecie(Especie.CACHORRO);
        petDTO.setNome("Totó");
        petDTO.setPorte(Porte.PEQUENO);
        petDTO.setSexo("macho");
        petDTO.setLocalizacao(localizacaoDTO);

        String petRetorno = mvc.perform(post("/pet/usuario/{usuario}", getUsuario().getId())
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
        petDTOResponse.setLocalizacao(localizacao);
        petDTOResponse.setUsuario(usuario);

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


    protected static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        JavaTimeModule module = new JavaTimeModule();
        mapper.registerModule(module);

        return mapper.writeValueAsBytes(object);
    }
}
