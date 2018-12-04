package br.com.academiadev.bumblebee.service;

import br.com.academiadev.bumblebee.model.Bairro;
import br.com.academiadev.bumblebee.repository.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BairroService extends ServiceAbstrataImpl<BairroRepository, Bairro, Long> {

    @Autowired
    public BairroService(BairroRepository repository) {
        super(repository);
    }

}
