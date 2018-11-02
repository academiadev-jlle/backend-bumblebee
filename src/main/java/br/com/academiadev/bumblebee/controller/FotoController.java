package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.model.Foto;
import br.com.academiadev.bumblebee.repository.FotoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foto")
@Api(description = "Fotos")
public class FotoController {

    @Autowired
    private FotoRepository repository;

    @ApiOperation(value = "Retorna uma lista de fotos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Fotos retornadas com sucesso")
    })
    @GetMapping
    public List<Foto> buscarTodos() {
        return repository.findAll();
    }

    @ApiOperation(value = "Retorna uma foto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Foto encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public Foto buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Foto com id " + id + " não encontrado"));
    }

    @ApiOperation(value = "Retorna uma lista de fotos do pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Fotos retornadas com sucesso")
    })
    @GetMapping("/pet/{idPet}")
    public List<Foto> buscarPorPet(@PathVariable Long idPet) throws ObjectNotFoundException {
        return repository.findAllByPetIdPet(idPet);
//                .orElseThrow(() -> new ObjectNotFoundException("Foto com id do pet " + idPet + " não encontrado"));
    }

    @ApiOperation(value = "Cria uma Foto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Foto criada com sucesso")
    })
    @PostMapping
    public Foto criar(@RequestBody Foto foto) {
        return repository.save(foto);
    }

    @ApiOperation(value = "Deleta um foto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Foto deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) throws ObjectNotFoundException {
        Foto foto = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Foto com id " + id + " não encontrado"));
        foto.setExcluido(Boolean.TRUE);
        repository.save(foto);
    }
}
