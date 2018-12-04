package br.com.academiadev.bumblebee.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "Localizacao")
@Entity
@SQLDelete(sql =
        "UPDATE Localizacao " +
                "SET excluido = true " +
                "WHERE id = ?")
@Where(clause="excluido=false")
public class Localizacao extends EntidadeAuditavel<Long>{

    @NotNull
    @Size(min = 1, max = 45)
    @ApiModelProperty(example = "Capinzal", name = "Logradouro")
    private String logradouro;

    @Size(max = 45)
    @ApiModelProperty(example = "Casa", name = "Referencia")
    private String referencia;

    @ManyToOne
    private Cidade cidade;

    @ManyToOne
    private Bairro bairro;

}
