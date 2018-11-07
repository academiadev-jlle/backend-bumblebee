package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/pet")
@Api(description = "Pets")
public class PetController extends CrudControllerAbstrato<PetService, Pet, Long> {

    @Autowired
    public PetController(PetService service){
        super(service);
    }

}
