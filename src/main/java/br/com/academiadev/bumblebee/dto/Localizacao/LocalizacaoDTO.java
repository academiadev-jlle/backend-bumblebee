package br.com.academiadev.bumblebee.dto.Localizacao;

import br.com.academiadev.bumblebee.model.Cidade;
import lombok.Data;

@Data
public class LocalizacaoDTO {

    private String logradouro;
    private String bairro;
    private String referencia;

}
