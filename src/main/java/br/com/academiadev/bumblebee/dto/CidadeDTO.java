package br.com.academiadev.bumblebee.dto;

import br.com.academiadev.bumblebee.model.Uf;
import lombok.Data;

@Data
public class CidadeDTO {

    private Long id;
    private String nome;
    private Uf uf;
    private Boolean excluido;

}
