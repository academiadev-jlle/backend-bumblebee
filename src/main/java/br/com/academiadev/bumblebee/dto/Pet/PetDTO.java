package br.com.academiadev.bumblebee.dto.Pet;

import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import br.com.academiadev.bumblebee.model.Foto;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.model.Usuario;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PetDTO {

    private String nome;
    private String descricao;
    private String Sexo;
    private Categoria categoria;
    private Porte porte;
    private Especie especie;
    private List<Foto> fotos;

}
