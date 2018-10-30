package br.com.academiadev.bumblebee.repository;

import br.com.academiadev.bumblebee.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
