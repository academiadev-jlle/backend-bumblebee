package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTOResponse;
import br.com.academiadev.bumblebee.dto.Comentario.ComentarioDTO;
import br.com.academiadev.bumblebee.dto.Comentario.ComentarioDTOResponse;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTOResponse;
import br.com.academiadev.bumblebee.model.Cidade;
import br.com.academiadev.bumblebee.model.Comentario;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, PetMapper.class})
public interface ComentarioMapper extends EntityMapper<Comentario, ComentarioDTO>{

    @Override
    ComentarioDTO toDTO(Comentario entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "excluido", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(source = "usuario", target = "usuario"),
            @Mapping(source = "pet", target = "pet"),
            @Mapping(source = "comentarioDTO.descricao", target = "descricao"),
    })
    Comentario toEntity(ComentarioDTO comentarioDTO, Pet pet, Usuario usuario);

    ComentarioDTOResponse toDTOResponse(Comentario entity);

    List<ComentarioDTOResponse> toDTOResponse(List<Comentario> entity);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(target = "excluido", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
//            @Mapping(source = "usuario", target = "usuario"),
//            @Mapping(source = "pet", target = "pet"),
            @Mapping(source = "comentarioDTO.descricao", target = "descricao"),
            @Mapping(source = "usuario", target = "usuario"),
            @Mapping(source = "pet", target = "pet"),
    })
    Comentario toEntity(ComentarioDTO comentarioDTO, Long id, Usuario usuario, Pet pet);
}
