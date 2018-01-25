package co.ceiba.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.parking.logica.Usuario;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, String>{
	
}
