package br.com.academiadev.bumblebee.endpoint;

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
public class UfEndpoint {

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
    public List<Uf> buscarPor(@PathVariable Long id) {
        return repository.findAll();
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
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
