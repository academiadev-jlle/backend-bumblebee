package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.service.ServiceAbstrata;
import io.swagger.annotations.*;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
public class CrudControllerAbstrato<S extends ServiceAbstrata<T, ID>, T, ID> extends ControllerAbstrata<S, T, ID> {

    public CrudControllerAbstrato(S service) {
        super(service);
    }

    @ApiOperation(value = "Cria uma entidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entidade criada com sucesso")
    })
    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody T entidade) {
        service.save(entidade);
        return ResponseEntity.ok(entidade);
    }

    @ApiOperation(value = "Retorna uma lista de entidades")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entidades retornadas com sucesso")
    })
//    @ApiImplicitParams({ //
//            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") //
//    })
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<T> buscarTodos() {
        return service.findAll();
    }

    @ApiOperation(value = "Retorna uma entidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entidade encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public T buscarPor(@PathVariable ID id) {
        return service.findById(id).orElse(null);
    }


    @ApiOperation(value = "Deleta uma entidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entidade deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable ID id) {
        service.deleteById(id);
    }
}
