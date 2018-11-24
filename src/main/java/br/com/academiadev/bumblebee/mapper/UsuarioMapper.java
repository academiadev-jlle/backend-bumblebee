package br.com.academiadev.bumblebee.mapper;

import br.com.academiadev.bumblebee.dto.UsuarioDTO;
import br.com.academiadev.bumblebee.dto.UsuarioDTOResponse;
import br.com.academiadev.bumblebee.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<Usuario, UsuarioDTO> {

    UsuarioDTO toDTO(Usuario entity);

    UsuarioDTOResponse toDTOResponse(Usuario entity);

    @Override
    Usuario toEntity(UsuarioDTO usuarioDTO);
}
