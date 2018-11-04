package br.com.academiadev.bumblebee.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Where(clause = "excluido=false")
public class Foto {

    @Id
    @GeneratedValue
    private Long idFoto;

    @NotNull
    private String arquivo;

    @NotNull
    @Column(name = "excluido")
    private Boolean excluido = false;

    public Foto() {

    }

    public Long getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Long idFoto) {
        this.idFoto = idFoto;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }


}
