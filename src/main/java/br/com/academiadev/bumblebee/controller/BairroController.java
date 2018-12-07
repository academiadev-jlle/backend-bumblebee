package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Bairro.BairroDTO;
import br.com.academiadev.bumblebee.dto.Bairro.BairroDTOResponse;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.mapper.BairroMapper;
import br.com.academiadev.bumblebee.model.Bairro;
import br.com.academiadev.bumblebee.service.BairroService;
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
@RequestMapping("/bairro")
@Api(description = "Bairros")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @Autowired
    private BairroMapper bairroMapper;


    @ApiOperation(value = "Retorna um bairro")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Bairro encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public BairroDTOResponse buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        Bairro bairro = bairroService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Bairro com id " + id + " não encontrado"));
        return bairroMapper.toDTOResponse(bairro);
    }

    @ApiOperation(value = "Cria um bairro")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Bairro criado com sucesso")
    })
    @PostMapping("/")
    public BairroDTOResponse criar(@RequestBody @Valid BairroDTO bairroDTO) {
        Bairro bairro = bairroMapper.toEntity(bairroDTO);
        bairroService.save(bairro);
        return bairroMapper.toDTOResponse(bairro);
    }

    @ApiOperation(value = "Buscar todos os bairros")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Bairros encontradas com sucesso")
    })
    @GetMapping("/bairros")
    public List<BairroDTOResponse> buscarTodos() {
        List<Bairro> listaBairro = bairroService.findAll();
        return bairroMapper.toDTOResponse(listaBairro);
    }

    @ApiOperation(value = "Deleta um bairro")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Bairro deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) throws ObjectNotFoundException {
        Bairro bairro = bairroService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cidade com id " + id + " não encontrado"));
        bairro.setExcluido(Boolean.TRUE);
        bairroService.save(bairro);
    }

}
