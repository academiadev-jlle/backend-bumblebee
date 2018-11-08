package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.FotoPetDTO;
import br.com.academiadev.bumblebee.model.FotoPet;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface FotoPetMapper extends EntityMapper<FotoPet, FotoPetDTO> {

    @Mappings({

            @Mapping(source = "createdAt", target = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    FotoPetDTO toDTO(FotoPet entity);

    @Mappings({
            @Mapping(target = "createdAt", source = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    FotoPet toEntity(FotoPetDTO fotoPetDTO);
}
