package br.com.academiadev.bumblebee.dto.Localizacao;

import lombok.Data;

@Data
public class LocalizacaoDTO {

    private String logradouro;
    private String referencia;
    private String uf;
    private String cidade;
    private String bairro;
    private String cep;

}
