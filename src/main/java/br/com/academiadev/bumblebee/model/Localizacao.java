package br.com.academiadev.bumblebee.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Where(clause="excluido=false")
public class Localizacao {

    @Id
    @GeneratedValue
    private Long idLocalizacao;

    @NotNull
    @Size(min = 1, max = 45)
    private String logradouro;

    @NotNull
    @Size(min = 1, max = 45)
    private String bairro;

    @Size(max = 45)
    private String referencia;

    @NotNull
    @Column(name="excluido")
    private Boolean excluido = false;

    @ManyToOne
    private Cidade cidade;

    public Localizacao(){

    }

    public Long getIdLocalizacao() {
        return idLocalizacao;
    }

    public void setIdLocalizacao(Long idLocalizacao) {
        this.idLocalizacao = idLocalizacao;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
