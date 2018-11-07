package br.com.academiadev.bumblebee.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Entidade<ID> implements EntidadeAbstrata<ID> {

    @Id
    @GeneratedValue
    @ApiModelProperty(example = "1", name = "Identificador")
    protected ID id;

}

