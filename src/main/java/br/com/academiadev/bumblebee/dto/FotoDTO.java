package br.com.academiadev.bumblebee.dto;

import lombok.Data;

@Data
public class FotoDTO {

    private Long id;
    private String arquivo;
    private Boolean excluido;

}
