package br.com.academiadev.bumblebee.repository;

import br.com.academiadev.bumblebee.model.Cidade;
import br.com.academiadev.bumblebee.model.Comentario;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findAllByPet(Pet pet);

    List<Comentario> findAllByUsuarioExcluidoAndPetExcluido(Boolean valorUsuario, Boolean valorPet);

    List<Comentario> findAllByPetAndUsuarioExcluidoAndPetExcluido(Pet pet, Boolean valorUsuario, Boolean valorPet);

}
