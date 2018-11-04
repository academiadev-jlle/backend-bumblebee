package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
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
public class PetController {

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
    public Pet buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pet com id " + id + " não encontrado"));
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
    public void deletar(@PathVariable Long id) throws ObjectNotFoundException {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pet com id " + id + " não encontrado"));
        pet.setExcluido(Boolean.TRUE);
        repository.save(pet);
    }

}
