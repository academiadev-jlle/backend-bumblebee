package br.com.academiadev.bumblebee.dto;

import br.com.academiadev.bumblebee.model.Foto;
import br.com.academiadev.bumblebee.model.Pet;
import lombok.Data;

@Data
public class FotoPetDTO {

    private Long id;
    private Pet pet;
    private Foto foto;
    private Boolean excluido;
}
