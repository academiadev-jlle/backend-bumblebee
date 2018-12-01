package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTO;
import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTOResponse;
import br.com.academiadev.bumblebee.model.Cidade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CidadeMapper extends EntityMapper<Cidade, CidadeDTO> {

    CidadeDTO toDTO(Cidade entity);

    CidadeDTOResponse toDTOResponse(Cidade entity);

    List<CidadeDTOResponse> toDTOResponse(List<Cidade> entity);

    @Override
    Cidade toEntity(CidadeDTO cidadeDTO);
}

