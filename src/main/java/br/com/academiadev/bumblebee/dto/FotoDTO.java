package br.com.academiadev.bumblebee.dto;

import br.com.academiadev.bumblebee.model.Pet;
import lombok.Data;

@Data
public class FotoDTO {

    private Pet pet;
    private byte[] foto;

}
