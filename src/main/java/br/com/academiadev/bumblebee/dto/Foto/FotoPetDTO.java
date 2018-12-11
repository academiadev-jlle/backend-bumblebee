package br.com.academiadev.bumblebee.dto.Foto;

import br.com.academiadev.bumblebee.model.Pet;
import lombok.Data;

@Data
public class FotoPetDTO {

    private byte[] foto;
    private Pet pet;

}
