package br.com.academiadev.bumblebee.model;

import io.swagger.annotations.ApiModel;
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

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "Foto")
@Entity
@SQLDelete(sql =
        "UPDATE Foto " +
                "SET excluido = true " +
                "WHERE id = ?")
@Where(clause = "excluido=false")
public class Foto extends EntidadeAuditavel<Long>{

//    @NotNull
//    @ApiModelProperty(example = "foto/pet.jpg", name = "Arquivo")
//    private String arquivo;

    //    @Lob
    @NotNull
    private byte[] foto;

    @ManyToOne(optional = true)
    private Pet pet;

}
