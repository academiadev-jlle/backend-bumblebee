package br.com.academiadev.bumblebee.repository;

import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByCategoria(Categoria categoria);

    Page<Pet> findAllByCategoria(Categoria categoria, Pageable pageable);

    @Query("select p from Pet p " +
            "where ((:busca is null or lower(p.nome) like %:busca%)" +
            "or (:busca is null or lower(p.descricao) like %:busca%))" +
            "and (p.categoria = :categoria or :categoria is null)" +
            "and (p.especie = :especie or :especie is null)" +
            "and (p.porte = :porte or :porte is null)" +
            "")
    Page<Pet>  findAllByFiltro(@Param("categoria") Categoria categoria,
                               @Param("especie") Especie especie,
                               @Param("porte") Porte porte,
                               @Param("busca") String busca,
                               Pageable pageable);

    List<Pet> findAllByUsuario(Usuario usuario);

    Page<Pet> findAllByUsuario(Usuario usuario, Pageable pageable);

    Page<Pet> findAll(Pageable pageable);
}
