package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.CidadeDTO;
import br.com.academiadev.bumblebee.model.Cidade;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface CidadeMapper extends EntityMapper<Cidade, CidadeDTO> {
    @Mappings({

            @Mapping(source = "createdAt", target = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    CidadeDTO toDTO(Cidade entity);

    @Mappings({
            @Mapping(target = "createdAt", source = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    Cidade toEntity(CidadeDTO cidadeDTO);
}
