package br.com.academiadev.bumblebee.dto.Localizacao;

import br.com.academiadev.bumblebee.dto.Bairro.BairroDTOResponse;
import br.com.academiadev.bumblebee.dto.Cidade.CidadeDTOResponse;
import lombok.Data;

@Data
public class LocalizacaoDTO {

    private String logradouro;
    private String referencia;
    private BairroDTOResponse bairro;
    private CidadeDTOResponse cidade;

}
