package br.com.academiadev.bumblebee.service;

import br.com.academiadev.bumblebee.model.Cidade;
import br.com.academiadev.bumblebee.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService extends ServiceAbstrataImpl<CidadeRepository, Cidade, Long>  {

    @Autowired
    public CidadeService(CidadeRepository repository) {
        super(repository);
    }

}
