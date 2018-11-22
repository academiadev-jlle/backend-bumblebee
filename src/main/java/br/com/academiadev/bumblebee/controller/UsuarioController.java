package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.UsuarioRepository;
import br.com.academiadev.bumblebee.service.UsuarioService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/usuario")
//@Api(description = "Usuarios")
//public class UsuarioController{
//
//    @Autowired
//    private UsuarioRepository repository;
//
//    @ApiOperation(value = "Retorna uma usuario")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Usuario encontrado com sucesso")
//    })
//    @GetMapping("/{id}")
//    public Usuario buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
//        return repository.findById(id)
//                .orElseThrow(() -> new ObjectNotFoundException("Usuário com id " + id + " não encontrado"));
//    }
//
//    @ApiOperation(value = "Cria um Usuário")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Usuário criado com sucesso")
//    })
//
//    @PostMapping
//    public Usuario criar(@RequestBody Usuario usuario) {
//        return repository.save(usuario);
//    }

//    @ApiOperation(value = "Deleta um Usuário")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Usuario deletado com sucesso")
//    })
//    @DeleteMapping("/{id}")
//    public Usuario deletar(@PathVariable Long id) throws ObjectNotFoundException {
//        Usuario usuario = repository.findById(id)
//                .orElseThrow(() -> new ObjectNotFoundException("Usuário com id " + id + " não encontrado"));
//        usuario.setExcluido(Boolean.TRUE);
//        repository.save(usuario);
//        return usuario;
//    }

//}


@RestController
@RequestMapping(value = "/usuario")
@Api(description = "Usuários")
public class UsuarioController extends CrudControllerAbstrato<UsuarioService, Usuario, Long> {

    @Autowired
    public UsuarioController(UsuarioService service) {
        super(service);
    }

    @Override
    @ApiImplicitParams({ //
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") //
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Usuario> buscarTodos() {
        return service.findAll();
    }


}
