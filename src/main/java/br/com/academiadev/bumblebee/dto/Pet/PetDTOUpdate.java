package br.com.academiadev.bumblebee.dto.Pet;

import br.com.academiadev.bumblebee.dto.Localizacao.LocalizacaoDTOUpdate;
import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import lombok.Data;

@Data
public class PetDTOUpdate {

    private Long id;
    private String nome;
    private String descricao;
    private String Sexo;
    private LocalizacaoDTOUpdate localizacao;
    private Categoria categoria;
    private Porte porte;
    private Especie especie;

}
