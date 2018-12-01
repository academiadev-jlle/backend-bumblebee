package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTO;
import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTOResponse;
import br.com.academiadev.bumblebee.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<Usuario, UsuarioDTO> {

    UsuarioDTO toDTO(Usuario entity);

    UsuarioDTOResponse toDTOResponse(Usuario entity);

    List<UsuarioDTOResponse> toDTOResponse(List<Usuario> entity);

    Usuario toEntityUpdate(UsuarioDTOResponse usuarioDTOResponse);

    @Override
    Usuario toEntity(UsuarioDTO usuarioDTO);

}
