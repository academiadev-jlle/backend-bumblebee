package br.com.academiadev.bumblebee.service;

import br.com.academiadev.bumblebee.model.Foto;
import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FotoService extends ServiceAbstrataImpl<FotoRepository, Foto, Long> {

    @Autowired
    public FotoService(FotoRepository repository){
        super(repository);
    }

    public List<Foto> findFotoByPet(Pet pet) {
        return getRepository().findAllFotoByPet(pet);
    }
}
