package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.service.UsuarioService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
