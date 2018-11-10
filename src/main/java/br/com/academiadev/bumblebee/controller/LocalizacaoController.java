package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.service.LocalizacaoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localizacao")
@Api(description = "Localizações")
public class LocalizacaoController extends CrudControllerAbstrato<LocalizacaoService, Localizacao, Long>{

    @Autowired
    public LocalizacaoController(LocalizacaoService service){
        super(service);
    }

}
