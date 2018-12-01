package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOResponse;
import br.com.academiadev.bumblebee.dto.Uf.UfDTO;
import br.com.academiadev.bumblebee.dto.Uf.UfDTOResponse;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.mapper.LocalizacaoMapper;
import br.com.academiadev.bumblebee.mapper.UfMapper;
import br.com.academiadev.bumblebee.model.Cidade;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Uf;
import br.com.academiadev.bumblebee.repository.LocalizacaoRepository;
import br.com.academiadev.bumblebee.repository.UfRepository;
import br.com.academiadev.bumblebee.service.CidadeService;
import br.com.academiadev.bumblebee.service.LocalizacaoService;
import br.com.academiadev.bumblebee.service.UfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/localizacao")
@Api(description = "Localizações")
public class LocalizacaoController{

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private LocalizacaoMapper localizacaoMapper;

    @Autowired
    private LocalizacaoService localizacaoService;

    @Autowired
    private CidadeService cidadeService;

    @ApiOperation(value = "Retorna um localização")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Localização encontrada com sucesso")
    })
    @GetMapping("/{id}")
    public LocalizacaoDTOResponse buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        Localizacao localizacao =  localizacaoService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Localização com id " + id + " não encontrado"));
        LocalizacaoDTOResponse localizacaoDTOResponse = localizacaoMapper.toDTOResponse(localizacao);
        return localizacaoDTOResponse;
    }

    @ApiOperation(value = "Cria um localização")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Localização criada com sucesso")
    })
    @PostMapping("/cidade/{cidade}")
    public LocalizacaoDTOResponse criar(@RequestBody @Valid LocalizacaoDTO localizacaoDTO,
                                        @PathVariable(value = "cidade") Long idCidade){
        Localizacao localizacao = localizacaoMapper.toEntity(localizacaoDTO);
        Cidade cidade = cidadeService.findById(idCidade).orElseThrow(()->new ObjectNotFoundException("Cidade não encontrada"));
        localizacao.setCidade(cidade);
        localizacaoService.save(localizacao);
        LocalizacaoDTOResponse localizacaoDTOResponse = localizacaoMapper.toDTOResponse(localizacao);
        return localizacaoDTOResponse;
    }

    @ApiOperation(value = "Buscar todas as localizações")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Localizações encontradas com sucesso")
    })
    @GetMapping("/localizacoes")
    public List<LocalizacaoDTOResponse> buscarTodos() {
        List<Localizacao> listaLocalizacao = localizacaoService.findAll();
        return localizacaoMapper.toDTOResponse(listaLocalizacao);
    }

    @ApiOperation(value = "Deleta uma localização")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Localização deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) throws ObjectNotFoundException {
        Localizacao localizacao = localizacaoService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Localização com id " + id + " não encontrado"));
        localizacao.setExcluido(Boolean.TRUE);
        localizacaoService.save(localizacao);
    }

}
