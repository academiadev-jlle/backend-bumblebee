package br.com.academiadev.bumblebee.dto.Usuario;

import lombok.Data;

@Data
public class UsuarioDTOResponse {

    private Long id;
    private String nome;
    private String email;
    private String contato;

}
