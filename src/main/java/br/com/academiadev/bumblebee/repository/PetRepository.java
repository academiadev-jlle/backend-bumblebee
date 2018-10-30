package br.com.academiadev.bumblebee.repository;

import br.com.academiadev.bumblebee.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface PetRepository extends JpaRepository<Pet, Long> {

}
