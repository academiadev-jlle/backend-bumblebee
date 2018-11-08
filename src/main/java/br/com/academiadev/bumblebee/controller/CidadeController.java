package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.model.Cidade;
import br.com.academiadev.bumblebee.service.CidadeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidade")
@Api(description = "Cidades")
public class CidadeController extends CrudControllerAbstrato<CidadeService, Cidade, Long>{

    @Autowired
    public CidadeController(CidadeService service){
        super(service);
    }

    @Autowired
    public void softDelete(Long id){
        service.softDelete(id);
    }

}
