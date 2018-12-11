package br.com.academiadev.bumblebee.service;

import br.com.academiadev.bumblebee.enums.Categoria;
import br.com.academiadev.bumblebee.enums.Especie;
import br.com.academiadev.bumblebee.enums.Porte;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService extends ServiceAbstrataImpl<PetRepository, Pet, Long> {

    @Autowired
    public PetService(PetRepository repository){
        super(repository);
    }

    public List<Pet> findAllByCategoria(Categoria categoria) {
        return getRepository().findAllByCategoria(categoria);
    }

    public List<Pet> findAllByUsuario(Usuario usuario){
        return getRepository().findAllByUsuario(usuario);
    }

    public Page<Pet> findAllByFiltro(String categoria, String especie, String porte, String busca, Pageable pageable){
        return getRepository().findAllByFiltro(categoria, especie, porte, busca, pageable);
    }
}
