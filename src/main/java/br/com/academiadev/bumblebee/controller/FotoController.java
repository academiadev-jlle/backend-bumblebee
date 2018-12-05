package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Uf.UfDTO;
import br.com.academiadev.bumblebee.dto.Uf.UfDTOResponse;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.model.Foto;
import br.com.academiadev.bumblebee.model.Uf;
import br.com.academiadev.bumblebee.repository.FotoRepository;
import br.com.academiadev.bumblebee.service.FotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/foto")
@Api(description = "Fotos")
public class FotoController{

//    @Autowired
//    private FotoRepository fotoRepository;

//    @ApiOperation(value = "Salva uma foto")
//    @ApiResponses(value = {
//            @ApiResponse(code = 201, message = "Foto salva com sucesso")
//    })
//    @PostMapping
//    public Long salvaFoto(@RequestBody Foto foto) {
//        fotoRepository.save(foto);
//        return foto.getId();
//    }

}
