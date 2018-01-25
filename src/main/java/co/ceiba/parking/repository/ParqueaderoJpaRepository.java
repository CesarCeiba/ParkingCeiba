package co.ceiba.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.parking.logica.Parqueadero;

public interface ParqueaderoJpaRepository extends JpaRepository<Parqueadero, Integer>{
	
}