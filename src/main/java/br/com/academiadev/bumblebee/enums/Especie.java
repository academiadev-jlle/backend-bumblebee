package br.com.academiadev.bumblebee.enums;

public enum Especie {
    AVE("Ave"), CACHORRO("Cachorro"), GATO("Gato"), PEIXE("Peixe");

    private String descricao;

    Especie(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
