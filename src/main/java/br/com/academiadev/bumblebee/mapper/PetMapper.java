package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.PetDTO;
import br.com.academiadev.bumblebee.model.Pet;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface PetMapper extends EntityMapper<Pet, PetDTO> {
    @Mappings({

            @Mapping(source = "createdAt", target = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    PetDTO toDTO(Pet entity);

    @Mappings({
            @Mapping(target = "createdAt", source = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    Pet toEntity(PetDTO petDTO);
}
