package br.com.academiadev.bumblebee.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "Foto")
@Entity
@Where(clause = "excluido=false")
public class Foto extends EntidadeAuditavel<Long>{

    @NotNull
    @ApiModelProperty(example = "foto/pet.jpg", name = "Arquivo")
    private String arquivo;

    @NotNull
    @Column(name = "excluido")
    private Boolean excluido = false;

}
