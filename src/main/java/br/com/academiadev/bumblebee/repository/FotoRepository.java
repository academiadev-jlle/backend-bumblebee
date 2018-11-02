package br.com.academiadev.bumblebee.repository;

import br.com.academiadev.bumblebee.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FotoRepository extends JpaRepository<Foto, Long> {
    List<Foto> findAllByPetIdPet(Long idPet);
}
