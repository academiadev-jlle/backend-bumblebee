package br.com.academiadev.bumblebee.dto.Pet;

import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import br.com.academiadev.bumblebee.model.Localizacao;
import lombok.Data;

@Data
public class PetDTOUpdate {

    private Long id;
    private String nome;
    private String descricao;
    private String Sexo;
    private Localizacao localizacao;
    private Categoria categoria;
    private Porte porte;
    private Especie especie;

}
