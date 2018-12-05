package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Uf.UfDTO;
import br.com.academiadev.bumblebee.dto.Uf.UfDTOResponse;
import br.com.academiadev.bumblebee.model.Uf;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UfMapper extends EntityMapper<Uf, UfDTO>{

    @Override
    UfDTO toDTO(Uf entity);

    @Override
    Uf toEntity(UfDTO ufDTO);

    UfDTOResponse toDTOResponse(Uf entity);

    List<UfDTOResponse> toDTOResponse(List<Uf> entity);
}
