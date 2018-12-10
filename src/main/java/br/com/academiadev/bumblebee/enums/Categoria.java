package br.com.academiadev.bumblebee.enums;

public enum Categoria {
    ACHADOS("Achados"),
    DEVOLVIDOS("Devolvidos"),
    PERDIDOS("Perdidos"),
    ENCONTRADOS("Encontrados"),
    ADOCAO("Adoção"),
    ADOTADOS("Adotados");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
