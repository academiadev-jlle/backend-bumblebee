package br.com.academiadev.bumblebee.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
public class Entidade<ID> implements EntidadeAbstrata<ID> {

    @Id
    @GeneratedValue
    @ApiModelProperty(example = "1", name = "Identificador")
    protected ID id;

    @NotNull
    @Column(name="excluido")
    @ApiModelProperty(hidden = true)
    private Boolean excluido = false;

}

