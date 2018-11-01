package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.model.Uf;
import br.com.academiadev.bumblebee.repository.UfRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uf")
@Api(description = "Uf's")
public class UfController {

    @Autowired
    private UfRepository repository;

    @ApiOperation(value = "Retorna uma lista de estados")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Estados retornados com sucesso")
    })
    @GetMapping
    public List<Uf> buscarTodos() {
        return repository.findAll();
    }

    @ApiOperation(value = "Retorna um estado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Estado encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public Uf buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Estado com id " + id + " não encontrado"));
    }

    @ApiOperation(value = "Cria um estado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Estado criado com sucesso")
    })

    @PostMapping
    public Uf criar(@RequestBody Uf uf) {
        return repository.save(uf);
    }

    @ApiOperation(value = "Deleta um estado")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Estado deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) throws ObjectNotFoundException {
        Uf uf = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Estado com id " + id + " não encontrado"));
        uf.setExcluido(Boolean.TRUE);
        repository.save(uf);
    }

}
