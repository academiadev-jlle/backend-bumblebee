package br.com.academiadev.bumblebee.enums;

public enum Categoria {
    ADOCAO("Adocao"), PROCURADO("Procurado"), ECONTRADO("Encontrado");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
