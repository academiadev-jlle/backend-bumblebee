package br.com.academiadev.bumblebee.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
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
