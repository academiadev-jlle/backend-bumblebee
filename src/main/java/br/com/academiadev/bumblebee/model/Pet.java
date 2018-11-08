package br.com.academiadev.bumblebee.model;

import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "Pet")
@Entity
@Where(clause="excluido=false")
public class Pet extends EntidadeAuditavel<Long>{

    @NotNull
    @Size(min = 1, max = 45)
    @ApiModelProperty(example = "Toto", name = "Nome")
    private String nome;

    @NotNull
    @Size(min = 1, max = 45)
    @ApiModelProperty(example = "Peludo e muito brincalhão", name = "Descricao")
    private String descricao;

    @NotNull
    @Size(min = 1, max = 1)
    @ApiModelProperty(example = "Macho", name = "Sexo")
    private String sexo;

    @NotNull
    private Date dataPostagem;

    @ManyToOne
    private Usuario usuario;

    @OneToOne
    private Localizacao localizacao;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private Porte porte;

    @Enumerated(EnumType.STRING)
    private Especie especie;

}
