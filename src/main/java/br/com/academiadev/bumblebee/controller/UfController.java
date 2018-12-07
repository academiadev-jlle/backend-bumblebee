package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Uf.UfDTO;
import br.com.academiadev.bumblebee.dto.Uf.UfDTOResponse;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.mapper.UfMapper;
import br.com.academiadev.bumblebee.model.Uf;
import br.com.academiadev.bumblebee.service.UfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/uf")
@Api(description = "UFs")
public class UfController {

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
        Uf uf = ufService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Uf com id " + id + " não encontrado"));
        return ufMapper.toDTOResponse(uf);
    }

    @ApiOperation(value = "Cria um Uf")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Uf criada com sucesso")
    })
    @PostMapping
    public UfDTOResponse criar(@RequestBody @Valid UfDTO ufDTO) {
        Uf uf = ufMapper.toEntity(ufDTO);
        ufService.save(uf);
        return ufMapper.toDTOResponse(uf);
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
