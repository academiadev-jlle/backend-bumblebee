package br.com.academiadev.bumblebee.repository;

import br.com.academiadev.bumblebee.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {
}
