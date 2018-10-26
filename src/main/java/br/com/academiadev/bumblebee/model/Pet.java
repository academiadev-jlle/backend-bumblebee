package br.com.academiadev.bumblebee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private Long idPet;

    @NotNull
    @Size(min = 1, max = 45)
    private String nome;

    @NotNull
    @Size(min = 1, max = 45)
    private String descricao;

    @NotNull
    @Size(min = 1, max = 1)
    private String sexo;

    @NotNull
    private Date data_postagem;

    @NotNull
    private Boolean excluido = false;

    @ManyToOne
    private Usuario usuario;

    @OneToOne
    private Localizacao localizacao;

    @OneToOne
    private Categoria categoria;

    @OneToOne
    private Porte porte;

    @OneToOne
    private Especie especie;

    public Pet(){

    }

    public Long getIdPet() {
        return idPet;
    }

    public void setIdPet(Long idPet) {
        this.idPet = idPet;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getData_postagem() {
        return data_postagem;
    }

    public void setData_postagem(Date data_postagem) {
        this.data_postagem = data_postagem;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }


}
