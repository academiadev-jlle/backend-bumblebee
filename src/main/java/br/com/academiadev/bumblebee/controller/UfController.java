package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Uf.UfDTO;
import br.com.academiadev.bumblebee.dto.Uf.UfDTOResponse;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTO;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTOResponse;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.mapper.UfMapper;
import br.com.academiadev.bumblebee.mapper.UsuarioMapper;
import br.com.academiadev.bumblebee.model.Uf;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.UfRepository;
import br.com.academiadev.bumblebee.repository.UsuarioRepository;
import br.com.academiadev.bumblebee.service.UfService;
import br.com.academiadev.bumblebee.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/uf")
@Api(description = "Uf's")
public class UfController{

    @Autowired
    private UfRepository repository;

    @Autowired
    private UfMapper ufMapper;

    @Autowired
    private UfService ufService;

    @ApiOperation(value = "Retorna um Uf")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Uf encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public UfDTOResponse buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        Uf uf =  ufService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Uf com id " + id + " não encontrado"));
        UfDTOResponse ufDTOResponse = ufMapper.toDTOResponse(uf);
        return ufDTOResponse;
    }

    @ApiOperation(value = "Cria um Uf")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Uf criada com sucesso")
    })
    @PostMapping
    public UfDTOResponse criar(@RequestBody @Valid UfDTO ufDTO) {
        Uf uf = ufMapper.toEntity(ufDTO);
        ufService.save(uf);
        UfDTOResponse ufDTOResponse = ufMapper.toDTOResponse(uf);
        return ufDTOResponse;
    }

    @ApiOperation(value = "Buscar todas as Ufs")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ufs encontradas com sucesso")
    })
    @GetMapping("/ufs")
    public List<UfDTOResponse> buscarTodos() {
        List<Uf> listaUsuario = ufService.findAll();
        return ufMapper.toDTOResponse(listaUsuario);
    }

    @ApiOperation(value = "Deleta uma Uf")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Uf deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) throws ObjectNotFoundException {
        Uf uf = ufService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Uf com id " + id + " não encontrado"));
        uf.setExcluido(Boolean.TRUE);
        ufService.save(uf);
    }

}
