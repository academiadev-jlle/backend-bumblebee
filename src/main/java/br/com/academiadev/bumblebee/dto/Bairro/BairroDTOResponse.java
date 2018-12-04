package br.com.academiadev.bumblebee.dto.Bairro;

import br.com.academiadev.bumblebee.model.Cidade;
import lombok.Data;

@Data
public class BairroDTOResponse {

    private Long id;
    private String nome;
    private Cidade cidade;

}
