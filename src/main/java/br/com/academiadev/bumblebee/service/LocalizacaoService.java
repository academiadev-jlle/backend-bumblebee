package br.com.academiadev.bumblebee.service;
import br.com.academiadev.bumblebee.model.Localizacao;
import br.com.academiadev.bumblebee.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService extends ServiceAbstrataImpl<LocalizacaoRepository, Localizacao, Long>{

    @Autowired
    public LocalizacaoService(LocalizacaoRepository repository) {
        super(repository);
    }

}