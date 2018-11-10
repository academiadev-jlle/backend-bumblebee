package br.com.academiadev.bumblebee.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
public class Entidade<ID> implements EntidadeAbstrata<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "1", name = "Identificador")
    protected ID id;

    @NotNull
    @Column(name="excluido")
    @ApiModelProperty(hidden = true)
    private Boolean excluido = false;

}

