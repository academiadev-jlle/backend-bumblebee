package br.com.academiadev.bumblebee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;

@Entity
public class Localizacao {

    @Id
    @GeneratedValue
    private Long idLocalizacao;

    @NotNull
    @Size(min = 1, max = 45)
    private String logradouro;

    @NotNull
    @Size(min = 1, max = 45)
    private String bairro;

    @Size(max = 45)
    private String referencia;

    @NotNull
    private Boolean excluido = false;

    @ManyToOne
    private Cidade cidade;

}
