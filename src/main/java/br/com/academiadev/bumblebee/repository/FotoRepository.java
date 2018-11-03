package br.com.academiadev.bumblebee.repository;

import br.com.academiadev.bumblebee.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Long> {
}
