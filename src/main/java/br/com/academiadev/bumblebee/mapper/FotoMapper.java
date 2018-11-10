package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.FotoDTO;
import br.com.academiadev.bumblebee.model.Foto;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface FotoMapper extends EntityMapper<Foto, FotoDTO> {

    @Mappings({

            @Mapping(source = "createdAt", target = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    FotoDTO toDTO(Foto entity);

    @Mappings({
            @Mapping(target = "createdAt", source = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    Foto toEntity(FotoDTO fotoDTO);
}
