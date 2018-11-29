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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;


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
public class Usuario extends EntidadeAuditavel<Long> implements UserDetails {

    @NotNull()
    @Size(min = 3, max = 120)
    @ApiModelProperty(example = "Bruno Muehlbauer de Souza", name = "Nome")
    private String nome;

    @NotNull
    @Size(min = 3, max = 60)
    @ApiModelProperty(example = "docsbruno@gmail.com", name = "E-mail")
    private String email;

    @NotNull
//    @Size(min = 6, max = 16)
    @ApiModelProperty(example = "123456", name = "Senha")
    private String senha;

    public Usuario(Long id) {
        this.id = id;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public String getPassword() {
        return senha;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public String getUsername() {
        return email;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public boolean isEnabled() {
        return true;
    }

}