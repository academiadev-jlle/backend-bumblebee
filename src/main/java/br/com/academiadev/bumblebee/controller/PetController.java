package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOResponse;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOResponse;
import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.mapper.FotoPetMapper;
import br.com.academiadev.bumblebee.mapper.LocalizacaoMapper;
import br.com.academiadev.bumblebee.mapper.PetMapper;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.LocalizacaoRepository;
import br.com.academiadev.bumblebee.repository.PetRepository;
import br.com.academiadev.bumblebee.service.LocalizacaoService;
import br.com.academiadev.bumblebee.service.PetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pet")
@Api(description = "Pets")
public class PetController{

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PetService petService;

    @ApiOperation(value = "Retorna um pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public PetDTOResponse buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        Pet pet =  petService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pet com id " + id + " não encontrado"));
        PetDTOResponse petDTOResponse = petMapper.toDTOResponse(pet);
        return petDTOResponse;
    }

    @ApiOperation(value = "Cria um pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet criada com sucesso")
    })
    @PostMapping
    public PetDTOResponse criar(@RequestBody @Valid PetDTO petDTO) {
        Pet pet = petMapper.toEntity(petDTO);
        Date now = new Date();
        pet.setDataPostagem(now);
        petService.save(pet);
        PetDTOResponse petDTOResponse = petMapper.toDTOResponse(pet);
        return petDTOResponse;
    }

    @ApiOperation(value = "Buscar todos as pets")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pets encontrados com sucesso")
    })
    @GetMapping("/pet")
    public List<PetDTOResponse> buscarTodos() {
        List<Pet> listaPets = petService.findAll();
        return petMapper.toDTOResponse(listaPets);
    }

    @ApiOperation(value = "Deleta um pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) throws ObjectNotFoundException {
        Pet pet = petService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pet com id " + id + " não encontrado"));
        pet.setExcluido(Boolean.TRUE);
        petService.save(pet);
    }

    @ApiOperation(value = "Retorna pets filtrados por categoria")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pets encontrados com sucesso")
    })
    @GetMapping("/categoria/{descricao}")
    public List<Pet> buscarPorCategoria(@PathVariable (value = "descricao") Categoria categoria) {
        return petService.findAllByCategoria(categoria);
    }

    @ApiOperation(value = "Retorna pets filtradas por usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pets encontrados com sucesso")
    })
    @GetMapping("/usuario/{usuario}")
    public List<Pet> buscarPorUsuario(@PathVariable (value = "usuario") Usuario usuario) {
        return petService.findAllByUsuario(usuario);
    }

}
