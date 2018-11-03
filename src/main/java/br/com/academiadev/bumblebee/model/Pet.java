package br.com.academiadev.bumblebee.model;

import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Where(clause="excluido=false")
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
    private Date dataPostagem;

    @NotNull
    @Column(name="excluido")
    private Boolean excluido = false;

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

    public Date getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(Date dataPostagem) {
        this.dataPostagem = dataPostagem;
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
