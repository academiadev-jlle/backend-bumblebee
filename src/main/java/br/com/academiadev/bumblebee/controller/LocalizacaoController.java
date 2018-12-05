package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOResponse;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.mapper.LocalizacaoMapper;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.service.BairroService;
import br.com.academiadev.bumblebee.service.CidadeService;
import br.com.academiadev.bumblebee.service.LocalizacaoService;
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
public class LocalizacaoController {

    @Autowired
    private LocalizacaoMapper localizacaoMapper;

    @Autowired
    private LocalizacaoService localizacaoService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private BairroService bairroService;

    @ApiOperation(value = "Retorna uma localização")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Localização encontrada com sucesso")
    })
    @GetMapping("/{id}")
    public LocalizacaoDTOResponse buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        Localizacao localizacao = localizacaoService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Localização com id " + id + " não encontrado"));
        return localizacaoMapper.toDTOResponse(localizacao);
    }

    @ApiOperation(value = "Cria uma localização")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Localização criada com sucesso")
    })
    @PostMapping("/")
    public LocalizacaoDTOResponse criar(@RequestBody @Valid LocalizacaoDTO localizacaoDTO) {
        Localizacao localizacao = localizacaoMapper.toEntity(localizacaoDTO);
        localizacaoService.save(localizacao);
        return localizacaoMapper.toDTOResponse(localizacao);
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

//    @ApiOperation(value = "Atualiza uma localização")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Localização criada com sucesso")
//    })
//    @PostMapping("atualiza/cidade/{cidade}")
//    public LocalizacaoDTOResponse atualizaLocalizacao(@RequestBody @Valid LocalizacaoDTOUpdate localizacaoDTO,
//                                                      @PathVariable(value = "cidade") Long idCidade) {
//        Localizacao localizacao = localizacaoMapper.toEntityUpdate(localizacaoDTO);
//        Cidade cidade = cidadeService.findById(idCidade).orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada"));
//        localizacao.setCidade(cidade);
//        localizacaoService.saveAndFlush(localizacao);
//        return localizacaoMapper.toDTOResponse(localizacao);
//    }

}
