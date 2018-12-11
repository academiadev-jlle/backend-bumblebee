package br.com.academiadev.bumblebee.repository;

import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByCategoria(Categoria categoria);

    Page<Pet> findAllByCategoria(Categoria categoria, Pageable pageable);

    @Query(value = "select p.id, excluido, created_at, updated_at, categoria, " +
            "datapostagem, descricao, especie, nome, porte, sexo, localizacao_id, usuario_id " +
            "from Pet p " +
            "where p.nome like %:busca% or " +
            "p.descricao like %:busca% or " +
            "p.categoria = :categoria and " +
            "p.especie = :especie and " +
            "p.porte = :porte", nativeQuery = true)
    Page<Pet> findAllByFiltro(String categoria, String especie, String porte, String busca, Pageable pageable);

    List<Pet> findAllByUsuario(Usuario usuario);

    Page<Pet> findAllByUsuario(Usuario usuario, Pageable pageable);

    Page<Pet> findAll(Pageable pageable);
}
