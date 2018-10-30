package br.com.academiadev.bumblebee.endpoint;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.repository.PetRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

@RestController
@RequestMapping("/pet")
@Api(description = "Pets")
public class PetEndpoint {

    @Autowired
    private PetRepository repository;

    @ApiOperation(value = "Retorna uma lista de Pets")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pets retornados com sucesso")
    })
    @GetMapping
    public List<Pet> buscarTodos() {
        return repository.findAll();
    }

    @ApiOperation(value = "Retorna um pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public List<Pet> buscarPor(@PathVariable Long id) {
        return repository.findAll();
    }

    @ApiOperation(value = "Cria um Pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet criado com sucesso")
    })

    @PostMapping
    public Pet criar(@RequestBody Pet pet) {
        return repository.save(pet);
    }

    @ApiOperation(value = "Deleta um pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
