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
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "Localizacao")
@Entity
@Where(clause="excluido=false")
public class Localizacao extends EntidadeAuditavel<Long>{

    @NotNull
    @Size(min = 1, max = 45)
    @ApiModelProperty(example = "Capinzal", name = "Logradouro")
    private String logradouro;

    @NotNull
    @Size(min = 1, max = 45)
    @ApiModelProperty(example = "Ubatuba", name = "Bairro")
    private String bairro;

    @Size(max = 45)
    @ApiModelProperty(example = "Casa", name = "Referencia")
    private String referencia;

    @ManyToOne
    private Cidade cidade;

}
