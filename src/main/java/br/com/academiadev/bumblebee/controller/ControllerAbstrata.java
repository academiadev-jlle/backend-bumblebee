package br.com.academiadev.bumblebee.controller;

import br.com.academiadev.bumblebee.service.ServiceAbstrata;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class ControllerAbstrata<S extends ServiceAbstrata<T, ID>, T, ID> {

    @Autowired
    protected S service;

    public ControllerAbstrata(S service) {
        service = service;
    }

}
