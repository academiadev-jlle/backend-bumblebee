package br.com.academiadev.bumblebee.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Where(clause="excluido=false")
public class Cidade {

    @Id
    @GeneratedValue
    private Long idCidade;

    @NotNull
    @Size(min = 1, max = 45)
    private String nome;

    @NotNull
    @Column(name="excluido")
    private Boolean excluido = false;

    @NotNull
    @ApiModelProperty(name = "Uf")
    @ManyToOne(optional = false)
    private Uf uf;


    public Cidade(){

    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

}
