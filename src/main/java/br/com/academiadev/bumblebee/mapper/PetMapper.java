package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOResponse;
import br.com.academiadev.bumblebee.dto.Pet.PetDTO;
import br.com.academiadev.bumblebee.dto.Pet.PetDTOResponse;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper extends EntityMapper<Pet, PetDTO> {
//    @Mappings({
//
//            @Mapping(source = "createdAt", target = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
//    })
    @Override
    PetDTO toDTO(Pet entity);

//    @Mappings({
//            @Mapping(target = "createdAt", source = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
//    })
    @Override
    Pet toEntity(PetDTO petDTO);

    PetDTOResponse toDTOResponse(Pet entity);

    List<PetDTOResponse> toDTOResponse(List<Pet> entity);
}
