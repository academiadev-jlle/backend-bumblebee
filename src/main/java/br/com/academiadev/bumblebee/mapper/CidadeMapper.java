package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTO;
import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTOResponse;
import br.com.academiadev.bumblebee.model.Cidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = UfMapper.class)
public interface CidadeMapper extends EntityMapper<Cidade, CidadeDTO> {

    CidadeDTO toDTO(Cidade entity);

    CidadeDTOResponse toDTOResponse(Cidade entity);

    List<CidadeDTOResponse> toDTOResponse(List<Cidade> entity);

    @Mappings({
            @Mapping(source = "uf", target = "uf")
    })
    Cidade toEntity(CidadeDTO cidadeDTO);
}

