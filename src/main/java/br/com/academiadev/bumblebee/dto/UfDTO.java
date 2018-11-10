package br.com.academiadev.bumblebee.dto;

import lombok.Data;

@Data
public class UfDTO {

    private Long id;
    private String nome;
    private String uf;
    private Boolean excluido = false;
}
