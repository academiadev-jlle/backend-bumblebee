package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.model.Uf;
import br.com.academiadev.bumblebee.service.UfService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/uf")
@Api(description = "Uf's")
public class UfController extends CrudControllerAbstrato<UfService, Uf, Long>{

    @Autowired
    public UfController(UfService service) {
        super(service);
    }

}
