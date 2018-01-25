package co.ceiba.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.parking.logica.Tarifa;

public interface TarifaJpaRepository extends JpaRepository<Tarifa, Integer> {
	
}
