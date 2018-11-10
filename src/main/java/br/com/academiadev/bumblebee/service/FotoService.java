package br.com.academiadev.bumblebee.service;

import br.com.academiadev.bumblebee.model.Foto;
import br.com.academiadev.bumblebee.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService extends ServiceAbstrataImpl<FotoRepository, Foto, Long> {

    @Autowired
    public FotoService(FotoRepository repository){
        super(repository);
    }

}
