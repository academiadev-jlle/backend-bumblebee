package br.com.academiadev.bumblebee.dto.Bairro;

import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTOResponse;
import lombok.Data;

@Data
public class BairroDTO {

    private String nome;
    private CidadeDTOResponse cidade;

}
