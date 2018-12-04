package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOResponse;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOResponse;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOUpdate;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTOResponse;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;
import org.apache.tomcat.jni.Local;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
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
            @Mapping(source = "now", target = "dataPostagem"),
            @Mapping(target = "createdAt", ignore = true),
    })
    Pet toEntity(PetDTO petDTO, Usuario usuario, Localizacao localizacao, Date now);

    PetDTOResponse toDTOResponse(Pet entity);

    @Mappings({
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(source = "petDTOUpdate.id", target = "id"),
            @Mapping(target = "excluido", ignore = true),
            @Mapping(source = "usuario", target = "usuario"),
            @Mapping(source = "petDTOUpdate.nome", target = "nome"),
            @Mapping(source = "now", target = "dataPostagem"),
            @Mapping(target = "createdAt", ignore = true),
    })
    Pet toEntityUpdate(PetDTOUpdate petDTOUpdate, Usuario usuario, Date now);

    List<PetDTOResponse> toDTOResponse(List<Pet> entity);
}
