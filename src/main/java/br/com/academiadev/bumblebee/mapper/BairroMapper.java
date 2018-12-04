package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Bairro.BairroDTO;
import br.com.academiadev.bumblebee.dto.Bairro.BairroDTOResponse;
import br.com.academiadev.bumblebee.model.Bairro;
import br.com.academiadev.bumblebee.model.Cidade;
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
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "excluido", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(source = "cidade", target = "cidade"),
            @Mapping(source = "bairroDTO.nome", target = "nome")
    })
    Bairro toEntity(BairroDTO bairroDTO, Cidade cidade);

}
