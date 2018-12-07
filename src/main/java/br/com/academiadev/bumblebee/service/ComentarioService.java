package br.com.academiadev.bumblebee.service;

import br.com.academiadev.bumblebee.model.Comentario;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.CompositeName;
import java.util.List;

@Service
public class ComentarioService extends ServiceAbstrataImpl<ComentarioRepository, Comentario, Long> {

    @Autowired
    public ComentarioService(ComentarioRepository repository) {
        super(repository);
    }

    public List<Comentario> findAllByPet(Pet pet){
        return getRepository().findAllByPet(pet);
    }

    public List<Comentario> buscaTodos (Boolean valorUsuario, Boolean valorPet){
        return getRepository().findAllByUsuarioExcluidoAndPetExcluido(valorUsuario, valorPet);
    }

    public List<Comentario> buscarPorPet (Pet pet, Boolean valorUsuario, Boolean valorPet){
        return getRepository().findAllByPetAndUsuarioExcluidoAndPetExcluido(pet, valorUsuario, valorPet);
    }
}
