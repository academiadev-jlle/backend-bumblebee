package br.com.academiadev.bumblebee.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "FotoPet")
@Entity
@SQLDelete(sql =
        "UPDATE FotoPet " +
                "SET excluido = true " +
                "WHERE id = ?")
@Where(clause = "excluido=false")
public class FotoPet extends EntidadeAuditavel<Long>{

    @NotNull
    @ManyToOne
    private Pet pet;

    @NotNull
    @OneToOne
    private Foto foto;

}
