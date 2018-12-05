package br.com.academiadev.bumblebee.service;

import br.com.academiadev.bumblebee.model.Comentario;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.model.Usuario;
import br.com.academiadev.bumblebee.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
