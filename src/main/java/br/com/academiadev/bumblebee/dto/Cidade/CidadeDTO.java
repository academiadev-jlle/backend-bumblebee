package br.com.academiadev.bumblebee.dto.Cidade;

import br.com.academiadev.bumblebee.dto.Uf.UfDTOResponse;
import lombok.Data;

@Data
public class CidadeDTO {

    private String nome;
    private UfDTOResponse uf;

}
