package co.ceiba.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.ceiba.parking.logica.Moto;

public interface MotoJpaRepository extends JpaRepository<Moto, String> {
	
	@Query(value = "SELECT count(1) FROM Vehiculo WHERE dtype = 'Moto' And hora_salida is null")
	int totalParqueados();
	
}
