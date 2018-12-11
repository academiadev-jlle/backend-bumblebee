package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Foto.FotoDTOResponse;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOResponse;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOUpdate;
import br.com.academiadev.bumblebee.dto.Pet.PetsDTOResponse;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring", uses = {LocalizacaoMapper.class, UsuarioMapper.class})
public interface PetMapper extends EntityMapper<Pet, PetDTO> {
//    @Mappings({
//
//            @Mapping(source = "createdAt", target = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
//    })
    @Override
    PetDTO toDTO(Pet entity);

    @Mappings({
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "excluido", ignore = true),
            @Mapping(source = "usuario", target = "usuario"),
            @Mapping(source = "petDTO.nome", target = "nome"),
            @Mapping(source = "localizacao", target = "localizacao"),
            @Mapping(source = "now", target = "datapostagem"),
            @Mapping(target = "createdAt", ignore = true),
    })
    Pet toEntity(PetDTO petDTO, Usuario usuario, Localizacao localizacao, Date now);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "excluido", ignore = true),
            @Mapping(source = "idPet", target = "id"),
            @Mapping(source = "petDTOUpdate.nome", target = "nome"),
            @Mapping(source = "petDTOUpdate.descricao", target = "descricao"),
            @Mapping(source = "petDTOUpdate.categoria", target = "categoria"),
            @Mapping(source = "petDTOUpdate.porte", target = "porte"),
            @Mapping(source = "petDTOUpdate.especie", target = "especie"),
            @Mapping(source = "petDTOUpdate.sexo", target = "sexo"),
            @Mapping(source = "usuario", target = "usuario"),
            @Mapping(source = "datapostagem", target = "datapostagem"),
            @Mapping(source = "localizacao", target = "localizacao"),
            @Mapping(source = "petDTOUpdate.localizacao.logradouro", target = "localizacao.logradouro"),
            @Mapping(source = "petDTOUpdate.localizacao.referencia", target = "localizacao.referencia")
    })
    Pet toEntity(PetDTOUpdate petDTOUpdate, Long idPet, Usuario usuario, Localizacao localizacao, Date datapostagem);

    @Mappings({
            @Mapping(source = "foto", target = "foto")
    })
    PetsDTOResponse toDTOPetsResponse(Pet entity, FotoDTOResponse foto);

    @Mappings({
            @Mapping(source = "fotos", target = "fotos")
    })
    PetDTOResponse toDTOResponse(Pet entity, List<FotoDTOResponse> fotos);

//    @Mappings({
//            @Mapping(target = "updatedAt", ignore = true),
//            @Mapping(source = "petDTOUpdate.id", target = "id"),
//            @Mapping(target = "excluido", ignore = true),
//            @Mapping(source = "usuario", target = "usuario"),
//            @Mapping(source = "petDTOUpdate.nome", target = "nome"),
//            @Mapping(source = "now", target = "datapostagem"),
//            @Mapping(target = "createdAt", ignore = true),
//    })
//    Pet toEntityUpdate(PetDTOUpdate petDTOUpdate, Usuario usuario, Date now);

    List<PetDTOResponse> toDTOResponse(List<Pet> entity);
}
