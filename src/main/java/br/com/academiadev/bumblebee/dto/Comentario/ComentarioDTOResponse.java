package br.com.academiadev.bumblebee.dto.Comentario;

import br.com.academiadev.bumblebee.dto.Usuario.UsuarioDTOResponse;
import lombok.Data;

@Data
public class ComentarioDTOResponse {

    private Long id;
    private String descricao;
    private UsuarioDTOResponse usuario;

}
