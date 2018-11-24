package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.service.PetService;
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
public class PetController extends CrudControllerAbstrato<PetService, Pet, Long> {

    @Autowired
    public PetController(PetService service){
        super(service);
    }

    @ApiOperation(value = "Retorna entidades filtrados por categoria")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entidades encontradas com sucesso")
    })
    @GetMapping("/categoria/{descricao}")
    public List<Pet> buscarPorCategoria(@PathVariable (value = "descricao") Categoria categoria) {
        return service.findAllByCategoria(categoria);
    }

    @ApiOperation(value = "Retorna entidades filtradas por usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entidades encontradas com sucesso")
    })
    @GetMapping("/usuario/{usuario}")
    public List<Pet> buscarPorUsuario(@PathVariable (value = "usuario") Usuario usuario) {
        return service.findAllByUsuario(usuario);
    }

}
