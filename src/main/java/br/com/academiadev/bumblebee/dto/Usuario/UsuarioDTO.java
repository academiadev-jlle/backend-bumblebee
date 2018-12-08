package br.com.academiadev.bumblebee.dto.Usuario;

import lombok.Data;


@Data
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private String contato;

}
