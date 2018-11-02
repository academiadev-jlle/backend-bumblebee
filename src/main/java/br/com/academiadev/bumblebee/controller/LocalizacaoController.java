package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.repository.LocalizacaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localizacao")
@Api(description = "Localizações")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoRepository repository;

    @ApiOperation(value = "Retorna uma lista de localizações")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Localizações retornadas com sucesso")
    })
    @GetMapping
    public List<Localizacao> buscarTodos() {
        return repository.findAll();
    }

    @ApiOperation(value = "Retorna uma localização")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Localização encontrada com sucesso")
    })
    @GetMapping("/{id}")
    public Localizacao buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Localização com id " + id + " não encontrada"));
    }

    @ApiOperation(value = "Cria uma localização")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Localização criada com sucesso")
    })

    @PostMapping
    public Localizacao criar(@RequestBody Localizacao localizacao) {
        return repository.save(localizacao);
    }

    @ApiOperation(value = "Deleta uma localização")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Localização deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    public Localizacao deletar(@PathVariable Long id) throws ObjectNotFoundException {
        Localizacao localizacao = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Localização com id " + id + " não encontrada"));
        localizacao.setExcluido(Boolean.TRUE);
        repository.save(localizacao);
        return localizacao;
    }

}
