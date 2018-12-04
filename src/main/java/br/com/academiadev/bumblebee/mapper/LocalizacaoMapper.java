package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOResponse;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOUpdate;
import br.com.academiadev.bumblebee.model.Bairro;
import br.com.academiadev.bumblebee.model.Cidade;
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
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "excluido", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(source = "cidade", target = "cidade"),
            @Mapping(source = "bairro", target = "bairro"),
    })
    Localizacao toEntity(LocalizacaoDTO localizacaoDTO, Bairro bairro, Cidade cidade);

    LocalizacaoDTOResponse toDTOResponse(Localizacao entity);

    List<LocalizacaoDTOResponse> toDTOResponse(List<Localizacao> entity);

    Localizacao toEntityUpdate(LocalizacaoDTOUpdate localizacaoDTOUpdate);


}
