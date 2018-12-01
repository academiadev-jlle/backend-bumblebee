package br.com.academiadev.bumblebee.dto.Pet;

import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Usuario;
import lombok.Data;

@Data
public class PetDTOResponse {

    private Long id;
    private String nome;
    private String descricao;
    private String Sexo;
    private Usuario usuario;
    private Localizacao localizacao;
    private Categoria categoria;
    private Porte porte;
    private Especie especie;

}
