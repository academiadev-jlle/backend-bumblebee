package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOResponse;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOUpdate;
import br.com.academiadev.bumblebee.model.Localizacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocalizacaoMapper extends EntityMapper<Localizacao, LocalizacaoDTO> {

    @Override
    LocalizacaoDTO toDTO(Localizacao entity);

    @Mappings({
            @Mapping(source = "cidade", target = "cidade"),
            @Mapping(source = "bairro", target = "bairro"),
    })
    Localizacao toEntity(LocalizacaoDTO localizacaoDTO);

    LocalizacaoDTOResponse toDTOResponse(Localizacao entity);

    List<LocalizacaoDTOResponse> toDTOResponse(List<Localizacao> entity);

    Localizacao toEntityUpdate(LocalizacaoDTOUpdate localizacaoDTOUpdate);


}
