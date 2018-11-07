package br.com.academiadev.bumblebee.service;

import br.com.academiadev.bumblebee.model.Pet;
import br.com.academiadev.bumblebee.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService extends ServiceAbstrataImpl<PetRepository, Pet, Long> {

    @Autowired
    public PetService(PetRepository repository){
        super(repository);
    }

}
