package br.com.academiadev.bumblebee.controller;
import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/enum")
@Api(description = "Enums")
public class EnumsController {

    @ApiOperation(value = "Buscar todas as Categorias")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Categorias encontradas com sucesso")
    })
    @GetMapping("/categorias")
    public Object[] buscarCategorias() {
        Object[] categorias = Categoria.values();
        return categorias;
    }

    @ApiOperation(value = "Buscar todas as Espécies")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Espécies encontradas com sucesso")
    })
    @GetMapping("/especies")
    public Object[] buscarEspecies() {
        Object[] especies = Especie.values();
        return especies;
    }

    @ApiOperation(value = "Buscar todos os Portes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Portes encontrados com sucesso")
    })
    @GetMapping("/portes")
    public Object[] buscarPortes() {
        Object[] portes = Porte.values();
        return portes;
    }


}
