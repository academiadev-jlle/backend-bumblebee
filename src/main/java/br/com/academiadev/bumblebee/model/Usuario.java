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
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "Usuario")
@Entity
@SQLDelete(sql =
        "UPDATE Usuario " +
                "SET excluido = true " +
                "WHERE id = ?")
@Where(clause="excluido=false")
public class Usuario extends EntidadeAuditavel<Long>{

    @NotNull()
    @Size(min = 3, max = 120)
    @ApiModelProperty(example = "Bruno Muehlbauer de Souza", name = "Nome")
    private String nome;

    @NotNull
    @Size(min = 3, max = 60)
    @ApiModelProperty(example = "docsbruno@gmail.com", name = "E-mail")
    private String email;

    @NotNull
    @Size(min = 6, max = 16)
    @ApiModelProperty(example = "123456", name = "Senha")
    private String senha;

    public Usuario(Long id) {
        this.id = id;
    }

}