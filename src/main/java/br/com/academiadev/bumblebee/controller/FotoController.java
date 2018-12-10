package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Foto.FotoDTOResponse;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.model.Foto;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.service.FotoService;
import br.com.academiadev.bumblebee.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

//import br.com.academiadev.bumblebee.mapper.FotoMapper;


@RestController
@RequestMapping("/foto")
@Api(description = "Fotos")
public class FotoController {


    @Autowired
    private FotoService fotoService;

//    @Autowired
//    private FotoMapper fotoMapper;

    @Autowired
    private PetService petService;

    @ApiOperation(value = "Salva uma foto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Foto salva com sucesso")
    })
    @PostMapping
    public Long salvaFoto(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = Base64.getEncoder().encode(file.getBytes());
        Foto foto = new Foto();
        foto.setFoto(bytes);
        fotoService.save(foto);
//        FotoDTO fotoDTO = new FotoDTO();
//        fotoDTO.setFoto(bytes);
//        Foto foto = fotoMapper.toEntity(fotoDTO);
//        fotoService.save(foto);
        return foto.getId();

    }

    @ApiOperation(value = "Deleta uma foto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Foto deletada com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) throws ObjectNotFoundException {
        Foto foto = fotoService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Foto não encontrada"));
        foto.setExcluido(Boolean.TRUE);
        fotoService.save(foto);
    }

    @ApiOperation(value = "Retorna fotos do pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Fotos encontrados com sucesso")
    })
    @GetMapping("/pet/{pet}")
    public List<FotoDTOResponse> buscarPorPet(@PathVariable(value = "pet") Long idPet) {
        Pet pet = petService.findById(idPet).orElseThrow(() -> new ObjectNotFoundException("Pet não encontrado"));
        List<Foto> fotos = fotoService.findFotoByPet(pet);
        List<FotoDTOResponse> fotosDTO = new ArrayList<>();
        for (Foto foto : fotos) {
            FotoDTOResponse fotoDTOResponse = new FotoDTOResponse();
            byte[] base = Base64.getDecoder().decode(foto.getFoto());
            fotoDTOResponse.setFoto(base);
            fotosDTO.add(fotoDTOResponse);
        }
        return fotosDTO;
    }


}
