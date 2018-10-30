package br.com.academiadev.bumblebee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Foto {

    @Id
    @GeneratedValue
    private Long idFoto;

    // TODO: verificar como salvar as imagens
    @NotNull
    @Size(min = 1, max = 45)
    private String nomeArquivo;

    @NotNull
    private Boolean excluido = false;

    @ManyToMany(targetEntity = Pet.class)
    private Set petSet;

    public Foto() {

    }

    public Long getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Long idFoto) {
        this.idFoto = idFoto;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

    public Set getPetSet() {
        return petSet;
    }

    public void setPetSet(Set petSet) {
        this.petSet = petSet;
    }

}
