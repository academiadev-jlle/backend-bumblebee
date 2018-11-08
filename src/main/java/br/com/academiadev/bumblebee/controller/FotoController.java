package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.model.Foto;
import br.com.academiadev.bumblebee.repository.FotoRepository;
import br.com.academiadev.bumblebee.service.FotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/foto")
@Api(description = "Fotos")
public class FotoController extends CrudControllerAbstrato<FotoService, Foto, Long> {

    @Autowired
    public FotoController(FotoService service){
        super(service);
    }

}
