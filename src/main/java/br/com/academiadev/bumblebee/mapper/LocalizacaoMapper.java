package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTOResponse;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTO;
import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOResponse;
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

    @Override
    Localizacao toEntity(LocalizacaoDTO localizacaoDTO);

    LocalizacaoDTOResponse toDTOResponse(Localizacao entity);

    List<LocalizacaoDTOResponse> toDTOResponse(List<Localizacao> entity);

}
