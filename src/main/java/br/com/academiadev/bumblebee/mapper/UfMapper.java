package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.UfDTO;
import br.com.academiadev.bumblebee.model.Uf;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface UfMapper extends EntityMapper<Uf, UfDTO>{
    @Mappings({

            @Mapping(source = "createdAt", target = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    UfDTO toDTO(Uf entity);

    @Mappings({
            @Mapping(target = "createdAt", source = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    Uf toEntity(UfDTO ufDTO);
}
