package br.com.academiadev.bumblebee.enums;

public enum Especie {
    PASSARO("Pássaro"),
    CACHORRO("Cachorro"),
    GATO("Gato"),
    OUTROS("Outros");

    private String descricao;

    Especie(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
