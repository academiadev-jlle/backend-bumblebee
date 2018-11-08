package br.com.academiadev.bumblebee.service;

import br.com.academiadev.bumblebee.model.Uf;
import br.com.academiadev.bumblebee.repository.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UfService extends ServiceAbstrataImpl<UfRepository, Uf, Long>{

    @Autowired
    public UfService(UfRepository repository) {
        super(repository);
    }

}
