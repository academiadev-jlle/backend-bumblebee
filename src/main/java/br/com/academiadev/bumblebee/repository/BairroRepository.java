package br.com.academiadev.bumblebee.repository;

import br.com.academiadev.bumblebee.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
}
