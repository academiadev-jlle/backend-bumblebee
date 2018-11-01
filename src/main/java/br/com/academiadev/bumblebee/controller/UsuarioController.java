package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@Api(description = "Usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @ApiOperation(value = "Retorna uma usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public Usuario buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário com id " + id + " não encontrado"));
    }

    @ApiOperation(value = "Cria um Usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário criado com sucesso")
    })
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @ApiOperation(value = "Deleta um Usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario deletado com sucesso")
    })
    @DeleteMapping("/{id]")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
