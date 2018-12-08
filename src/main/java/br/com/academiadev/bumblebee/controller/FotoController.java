package br.com.academiadev.bumblebee.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
