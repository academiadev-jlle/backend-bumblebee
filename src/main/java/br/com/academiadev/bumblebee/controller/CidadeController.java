package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.model.Cidade;
import br.com.academiadev.bumblebee.repository.CidadeRepository;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidade")
@Api(description = "Cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository repository;

    @ApiOperation(value = "Retorna uma lista de cidades")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cidades retornadas com sucesso")
    })
    @GetMapping
    public List<Cidade> buscarTodos() {
        return repository.findAll();
    }

    @ApiOperation(value = "Retorna uma cidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cidade encontrada com sucesso")
    })
    @GetMapping("/{id}")
    public Cidade buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cidade com id " + id + "não encontrada"));
    }

    @ApiOperation(value = "Cria uma cidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cidade criada com sucesso")
    })

    @PostMapping
    public Cidade criar(@RequestBody Cidade cidade) {
        return repository.save(cidade);
    }

    @ApiOperation(value = "Deleta uma cidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cidade deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) throws ObjectNotFoundException {
        Cidade cidade = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cidade com id " + id + "não encontrada"));
        cidade.setExcluido(Boolean.TRUE);
        repository.save(cidade);
    }

}
