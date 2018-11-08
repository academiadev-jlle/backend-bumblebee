package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.LocalizacaoDTO;
import br.com.academiadev.bumblebee.model.Localizacao;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface LocalizacaoMapper extends EntityMapper<Localizacao, LocalizacaoDTO> {
    @Mappings({

            @Mapping(source = "createdAt", target = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    LocalizacaoDTO toDTO(Localizacao entity);

    @Mappings({
            @Mapping(target = "createdAt", source = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    Localizacao toEntity(LocalizacaoDTO localizacaoDTO);
}
