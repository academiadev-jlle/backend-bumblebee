package br.com.academiadev.bumblebee.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private Boolean excluido = false;

}
