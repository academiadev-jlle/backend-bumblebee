package br.com.academiadev.bumblebee.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Where(clause = "excluido=false")
public class FotoPet {

    @Id
    @GeneratedValue
    private Long idFotoPet;

    @NotNull
    @ManyToOne
    private Pet pet;

    @NotNull
    @OneToOne
    private Foto foto;

    @NotNull
    @Column(name = "excluido")
    private Boolean excluido = false;

    public Long getIdFotoPet() {
        return idFotoPet;
    }

    public void setIdFotoPet(Long idFotoPet) {
        this.idFotoPet = idFotoPet;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

}
