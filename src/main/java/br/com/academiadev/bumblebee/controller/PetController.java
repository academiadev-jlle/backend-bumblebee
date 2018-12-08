package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOResponse;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOUpdate;
import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.mapper.LocalizacaoMapper;
import br.com.academiadev.bumblebee.mapper.PetMapper;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.PetRepository;
import br.com.academiadev.bumblebee.service.LocalizacaoService;
import br.com.academiadev.bumblebee.service.PetService;
import br.com.academiadev.bumblebee.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/pet")
@Api(description = "Pets")
public class PetController{

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PetService petService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LocalizacaoService localizacaoService;

    @Autowired
    private LocalizacaoMapper localizacaoMapper;

    @Autowired
    private PetRepository petRepository;

    @ApiOperation(value = "Retorna um pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public PetDTOResponse buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        Pet pet =  petService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pet com id " + id + " não encontrado"));
        return petMapper.toDTOResponse(pet);
    }

    @ApiOperation(value = "Cria um pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet criado com sucesso")
    })
    @PostMapping("/usuario/{usuario}")
    public PetDTOResponse criar(@RequestBody @Valid PetDTO petDTO,
                                @PathVariable(value = "usuario") Long idUsuario) {
//                                @RequestParam("files") MultipartFile[] files) throws IOException {
        Usuario usuario = usuarioService.findById(idUsuario).orElseThrow(()->new ObjectNotFoundException("Usuário não encontrado"));
        LocalizacaoDTO localizacaoDTO = petDTO.getLocalizacao();

        Localizacao localizacao = localizacaoService.save(localizacaoMapper.toEntity(localizacaoDTO));
        Date now = new Date();
        Pet pet = petMapper.toEntity(petDTO, usuario, localizacao, now);
        petService.save(pet);

//        for (MultipartFile file : files) {
//
//            if (file.isEmpty()) {
//                continue; //next pls
//            }
//
//            byte[] bytes = file.getBytes();
//            FotoDTO fotoDTO = new FotoDTO();
//            fotoDTO.setPet(pet);
//            fotoDTO.setFoto(bytes);
//            fotoService.save(fotoMapper.toEntity(fotoDTO));
//
//        }


//        for (FotoPetDTO fotoPetDTO : petDTO.getFotos()) {
//            FotoDTO fotoDTO = new FotoDTO();
//            fotoDTO.setFoto(FileCopyUtils.copyToByteArray(fotoPetDTO.getFoto()));
//            fotoDTO.setPet(pet);
//            fotoService.save(fotoMapper.toEntity(fotoDTO));

//            foto.setPet(pet);
//            fotoRepository.saveAndFlush(foto);
//        }

        return petMapper.toDTOResponse(pet);
    }

    @ApiOperation(value = "Buscar todos as pets")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pets encontrados com sucesso")
    })
    @GetMapping("/pets")
    public PageImpl<PetDTOResponse> buscarTodos(@RequestParam(defaultValue = "0") int paginaAtual,
                                                @RequestParam(defaultValue = "10") int tamanho,
                                                @RequestParam(defaultValue = "ASC") Sort.Direction direcao,
                                                @RequestParam(defaultValue = "dataPostagem") String campoOrdenacao) {
        PageRequest paginacao = PageRequest.of(paginaAtual, tamanho, direcao, campoOrdenacao);
        Page<Pet> listaPets = petRepository.findAll(paginacao);
        int totalDeElementos = (int) listaPets.getTotalElements();
        return new PageImpl<PetDTOResponse>(listaPets.stream().map(pet -> petMapper.toDTOResponse(pet)).collect(Collectors.toList()),paginacao,totalDeElementos);
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

    @ApiOperation(value = "Retorna pets filtrados")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pets encontrados com sucesso")
    })
    @GetMapping("/filtro")
    public PageImpl<PetDTOResponse> buscarPorFiltro(
            @RequestParam("categoria") Categoria categoria,
            @RequestParam("especie") Especie especie,
            @RequestParam("porte") Porte porte,
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "ASC") Sort.Direction direcao,
            @RequestParam(defaultValue = "dataPostagem") String campoOrdenacao) {
        PageRequest paginacao = PageRequest.of(paginaAtual, tamanho, direcao, campoOrdenacao);
        Page<Pet> listaPets = petRepository.findAllByCategoriaAndEspecieAndPorte(categoria, especie, porte, paginacao);
        int totalDeElementos = (int) listaPets.getTotalElements();
        return new PageImpl<PetDTOResponse>(listaPets.stream().map(pet -> petMapper.toDTOResponse(pet)).collect(Collectors.toList()),paginacao,totalDeElementos);

    }

    @ApiOperation(value = "Retorna pets filtradas por usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pets encontrados com sucesso")
    })
    @GetMapping("/usuario/{usuario}")
    public PageImpl<PetDTOResponse> buscarPorUsuario(@PathVariable(value = "usuario") Long idUsuario,
                                                     @RequestParam(defaultValue = "0") int paginaAtual,
                                                     @RequestParam(defaultValue = "10") int tamanho,
                                                     @RequestParam(defaultValue = "ASC") Sort.Direction direcao,
                                                     @RequestParam(defaultValue = "dataPostagem") String campoOrdenacao) {
        Usuario usuario = usuarioService.findById(idUsuario).orElseThrow(() -> new ObjectNotFoundException("Usuário com id " + idUsuario + " não encontrado"));
        PageRequest paginacao = PageRequest.of(paginaAtual, tamanho, direcao, campoOrdenacao);
        Page<Pet> listaPets = petRepository.findAllByUsuario(usuario, paginacao);
        int totalDeElementos = (int) listaPets.getTotalElements();
        return new PageImpl<PetDTOResponse>(listaPets.stream().map(pet -> petMapper.toDTOResponse(pet)).collect(Collectors.toList()),paginacao,totalDeElementos);

    }

    @ApiOperation(value = "Atualiza um pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet criada com sucesso")
    })
    @PostMapping("/atualizar/{pet}")
    public PetDTOResponse atualizarPet(@RequestBody @Valid PetDTOUpdate petDTOUpdate,
                                       @PathVariable(value = "pet") Long idPet) {
        Pet pet = petService.findById(idPet).orElseThrow(() -> new ObjectNotFoundException("Pet com id " + idPet + " não encontrado"));

        // todo: verificar se está correto
        Localizacao localizacao = localizacaoService.findById(pet.getLocalizacao().getId()).orElseThrow(() -> new ObjectNotFoundException("Localização não encontrado"));
        localizacao.setLogradouro(petDTOUpdate.getLocalizacao().getLogradouro());
        localizacao.setReferencia(petDTOUpdate.getLocalizacao().getReferencia());
        localizacao = localizacaoService.save(localizacao);

        pet = petService.save(petMapper.toEntity(petDTOUpdate, idPet, pet.getUsuario(), localizacao, pet.getDataPostagem()));

        return petMapper.toDTOResponse(pet);
    }

}
