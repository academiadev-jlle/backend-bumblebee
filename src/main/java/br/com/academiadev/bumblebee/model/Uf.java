package br.com.academiadev.bumblebee.model;

import io.swagger.annotations.ApiModel;
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
@ApiModel(description = "Usuário")
@Entity
@Where(clause="excluido=false")
public class Uf extends EntidadeAuditavel<Long>{

//    @Id
//    @GeneratedValue
//    private Long idUf;

    @NotNull
    @Size(min = 1, max = 45)
    private String nome;

    @NotNull
    @Size(min = 1, max = 45)
    private String uf;

    @NotNull
    @Column(name="excluido")
    private Boolean excluido = false;

//    public Uf(){
//
//    }

//    public Long getIdUf() {
//        return idUf;
//    }
//
//    public void setIdUf(Long idUf) {
//        this.idUf = idUf;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public String getUf() {
//        return uf;
//    }
//
//    public void setUf(String uf) {
//        this.uf = uf;
//    }
//
//    public Boolean getExcluido() {
//        return excluido;
//    }
//
//    public void setExcluido(Boolean excluido) {
//        this.excluido = excluido;
//    }

}
