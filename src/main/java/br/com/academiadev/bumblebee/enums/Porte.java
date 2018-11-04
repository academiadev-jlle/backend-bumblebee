package br.com.academiadev.bumblebee.enums;

public enum Porte {
    PEQUENO("Pequeno"), MEDIO("Medio"), GRANDE("Grande");

    private String descricao;

    Porte(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
