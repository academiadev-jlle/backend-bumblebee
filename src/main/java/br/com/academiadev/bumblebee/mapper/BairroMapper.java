package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Bairro.BairroDTO;
import br.com.academiadev.bumblebee.dto.Bairro.BairroDTOResponse;
import br.com.academiadev.bumblebee.model.Bairro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BairroMapper extends EntityMapper<Bairro, BairroDTO> {

    BairroDTO toDTO(Bairro entity);

    BairroDTOResponse toDTOResponse(Bairro entity);

    List<BairroDTOResponse> toDTOResponse(List<Bairro> entity);

    @Mappings({
            @Mapping(source = "cidade", target = "cidade"),
    })
    Bairro toEntity(BairroDTO bairroDTO);

}
