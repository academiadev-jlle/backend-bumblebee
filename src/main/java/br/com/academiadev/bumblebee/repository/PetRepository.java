package br.com.academiadev.bumblebee.repository;

import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByCategoria(Categoria categoria);

    Page<Pet> findAllByCategoria(Categoria categoria, Pageable pageable);

    List<Pet> findAllByUsuario(Usuario usuario);

    Page<Pet> findAllByUsuario(Usuario usuario, Pageable pageable);

    Page<Pet> findAll(Pageable pageable);
}
