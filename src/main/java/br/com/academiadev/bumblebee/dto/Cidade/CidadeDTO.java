package br.com.academiadev.bumblebee.dto.Cidade;

import br.com.academiadev.bumblebee.model.Uf;
import lombok.Data;

@Data
public class CidadeDTO {

    private String nome;
    private Uf uf;

}
