package br.com.academiadev.bumblebee.endpoint;

import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@Api(description = "Usuarios")
public class UsuarioEndpoint {

    @Autowired
    private UsuarioRepository repository;

    @ApiOperation(value = "Retorna uma usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public List<Usuario> buscarPor(@PathVariable Long id) {
        return repository.findAll();
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
