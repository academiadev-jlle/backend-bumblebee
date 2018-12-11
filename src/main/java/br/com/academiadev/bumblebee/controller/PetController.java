package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.dto.Foto.FotoDTOResponse;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOResponse;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOUpdate;
import br.com.academiadev.bumblebee.dto.Pet.PetsDTOResponse;
import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.bumblebee.mapper.LocalizacaoMapper;
import br.com.academiadev.bumblebee.mapper.PetMapper;
import br.com.academiadev.bumblebee.model.Foto;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.PetRepository;
import br.com.academiadev.bumblebee.service.FotoService;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//import br.com.academiadev.bumblebee.mapper.FotoMapper;

@CrossOrigin
@RestController
@RequestMapping("/pet")
@Api(description = "Pets")
public class PetController {

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

//    @Autowired
//    private FotoMapper fotoMapper;

    @Autowired
    private FotoService fotoService;

    @ApiOperation(value = "Retorna um pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public PetDTOResponse buscarPor(@PathVariable Long id) throws ObjectNotFoundException {
        Pet pet = petService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pet com id " + id + " não encontrado"));

        //TODAS IMGS
        List<Foto> fotos = fotoService.findFotoByPet(pet);
        List<FotoDTOResponse> fotoDTOResponse = new ArrayList<>();
        for (Foto foto : fotos) {
            FotoDTOResponse fotoDTO = new FotoDTOResponse();
            fotoDTO.setFoto(Base64.getDecoder().decode(foto.getFoto()));
            fotoDTOResponse.add(fotoDTO);
        }
        return petMapper.toDTOResponse(pet, fotoDTOResponse);
    }

    @ApiOperation(value = "Cria um pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet criado com sucesso")
    })
    @PostMapping("/usuario/{usuario}")
    public PetsDTOResponse criar(@RequestBody @Valid PetDTO petDTO,
                                 @PathVariable(value = "usuario") Long idUsuario) {
        Usuario usuario = usuarioService.findById(idUsuario).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        LocalizacaoDTO localizacaoDTO = petDTO.getLocalizacao();

        Localizacao localizacao = localizacaoService.save(localizacaoMapper.toEntity(localizacaoDTO));
        Date now = new Date();
        Pet pet = petMapper.toEntity(petDTO, usuario, localizacao, now);
        petService.save(pet);

        FotoDTOResponse fotoPetDTO = new FotoDTOResponse();
        byte[] base;
        // todo: melhorar
        if (petDTO.getIdFotos() != null) {
            Foto fotoPet = fotoService.findById(petDTO.getIdFotos().get(0)).orElseThrow(() -> new ObjectNotFoundException("Foto não encontrado"));
            base = Base64.getDecoder().decode(fotoPet.getFoto());
            fotoPetDTO.setFoto(base);

            for (Long id : petDTO.getIdFotos()) {
                Foto foto = fotoService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Foto não encontrado"));
                foto.setPet(pet);
                fotoService.save(foto);
            }
        }
//        else {
//            if(fotoService.getRepository().count()==0){
//                base = "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";
//            }
//        }

        return petMapper.toDTOPetsResponse(pet, fotoPetDTO);
    }

    @ApiOperation(value = "Buscar todos as pets")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pets encontrados com sucesso")
    })
    @GetMapping("/pets")
    public PageImpl<PetsDTOResponse> buscarTodos(@RequestParam(defaultValue = "0") int paginaAtual,
                                                 @RequestParam(defaultValue = "10") int tamanho,
                                                 @RequestParam(defaultValue = "ASC") Sort.Direction direcao,
                                                 @RequestParam(defaultValue = "datapostagem") String campoOrdenacao) {
        PageRequest paginacao = PageRequest.of(paginaAtual, tamanho, direcao, campoOrdenacao);
        Page<Pet> listaPets = petRepository.findAll(paginacao);
        int totalDeElementos = (int) listaPets.getTotalElements();
        //UMA IMG
        FotoDTOResponse fotoPetDTO = new FotoDTOResponse();
        return getPetsDTOResponses(paginacao, listaPets, totalDeElementos, fotoPetDTO);
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
    public PageImpl<PetsDTOResponse> buscarPorFiltro(
            @RequestParam(defaultValue = "") String busca,
            @RequestParam("categoria") Categoria categoria,
            @RequestParam("especie") Especie especie,
            @RequestParam("porte") Porte porte,
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "ASC") Sort.Direction direcao,
            @RequestParam(defaultValue = "datapostagem") String campoOrdenacao) {
        PageRequest paginacao = PageRequest.of(paginaAtual, tamanho, direcao, campoOrdenacao);

        Page<Pet> listaPets = petService.findAllByFiltro(categoria.getDescricao(), especie.getDescricao(), porte.getDescricao(), busca, paginacao);
        int totalDeElementos = (int) listaPets.getTotalElements();
        // UMA IMG
        FotoDTOResponse fotoPetDTO = new FotoDTOResponse();
        return getPetsDTOResponses(paginacao, listaPets, totalDeElementos, fotoPetDTO);

    }

    @ApiOperation(value = "Retorna pets filtradas por usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pets encontrados com sucesso")
    })
    @GetMapping("/usuario/{usuario}")
    public PageImpl<PetsDTOResponse> buscarPorUsuario(@PathVariable(value = "usuario") Long idUsuario,
                                                      @RequestParam(defaultValue = "0") int paginaAtual,
                                                      @RequestParam(defaultValue = "10") int tamanho,
                                                      @RequestParam(defaultValue = "ASC") Sort.Direction direcao,
                                                      @RequestParam(defaultValue = "datapostagem") String campoOrdenacao) {
        Usuario usuario = usuarioService.findById(idUsuario).orElseThrow(() -> new ObjectNotFoundException("Usuário com id " + idUsuario + " não encontrado"));
        PageRequest paginacao = PageRequest.of(paginaAtual, tamanho, direcao, campoOrdenacao);
        Page<Pet> listaPets = petRepository.findAllByUsuario(usuario, paginacao);
        int totalDeElementos = (int) listaPets.getTotalElements();

        // UMA IMG
        FotoDTOResponse fotoPetDTO = new FotoDTOResponse();

        return getPetsDTOResponses(paginacao, listaPets, totalDeElementos, fotoPetDTO);

    }

    @ApiOperation(value = "Atualiza um pet")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pet criada com sucesso")
    })
    @PostMapping("/atualizar/{pet}")
    public PetDTOResponse atualizarPet(@RequestBody @Valid PetDTOUpdate petDTOUpdate,
                                       @PathVariable(value = "pet") Long idPet) {
        Pet pet = petService.findById(idPet).orElseThrow(() -> new ObjectNotFoundException("Pet com id " + idPet + " não encontrado"));

        Localizacao localizacao = localizacaoService.findById(pet.getLocalizacao().getId()).orElseThrow(() -> new ObjectNotFoundException("Localização não encontrado"));
        localizacao.setLogradouro(petDTOUpdate.getLocalizacao().getLogradouro());
        localizacao.setReferencia(petDTOUpdate.getLocalizacao().getReferencia());
        localizacao = localizacaoService.save(localizacao);

        pet = petService.save(petMapper.toEntity(petDTOUpdate, idPet, pet.getUsuario(), localizacao, pet.getDatapostagem()));

        // TODAS IMGS
        List<Foto> fotos = fotoService.findFotoByPet(pet);
        List<FotoDTOResponse> fotoDTOResponse = new ArrayList<>();
        for (Foto foto : fotos) {
            FotoDTOResponse fotoDTO = new FotoDTOResponse();
            fotoDTO.setFoto(Base64.getDecoder().decode(foto.getFoto()));
            fotoDTOResponse.add(fotoDTO);
        }
        return petMapper.toDTOResponse(pet, fotoDTOResponse);
    }

    private PageImpl<PetsDTOResponse> getPetsDTOResponses(PageRequest paginacao, Page<Pet> listaPets, int totalDeElementos, FotoDTOResponse fotoPetDTO) {
        return new PageImpl<PetsDTOResponse>(listaPets.stream().map(pet -> {
            List<Foto> fotoPet = fotoService.findFotoByPet(pet);
            if (!fotoPet.isEmpty()) {
                byte[] base = Base64.getDecoder().decode(fotoPet.get(0).getFoto());
                fotoPetDTO.setFoto(base);
            }
            return petMapper.toDTOPetsResponse(pet, fotoPetDTO);
        }).collect(Collectors.toList()), paginacao, totalDeElementos);
    }

}
