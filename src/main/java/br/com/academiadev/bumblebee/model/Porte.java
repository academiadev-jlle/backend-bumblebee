package br.com.academiadev.bumblebee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Porte {

    @Id
    @GeneratedValue
    private Long idPorte;

    @NotNull
    @Size(min = 1, max = 45)
    private String descricao;

    @NotNull
    private Boolean excluido = false;

    public Porte(){

    }

    public Long getIdPorte() {
        return idPorte;
    }

    public void setIdPorte(Long idPorte) {
        this.idPorte = idPorte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

}
