package br.com.academiadev.bumblebee.repository;

import br.com.academiadev.bumblebee.model.Foto;
import br.com.academiadev.bumblebee.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
    List<Foto> findAllFotoByPet(Pet pet);
}
