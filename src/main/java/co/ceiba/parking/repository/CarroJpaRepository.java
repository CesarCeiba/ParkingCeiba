package co.ceiba.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.ceiba.parking.logica.Carro;

public interface CarroJpaRepository extends JpaRepository<Carro, String> {

	@Query(value = "SELECT count(1) FROM Vehiculo WHERE dtype = 'Carro' And hora_salida is null")
	int totalParqueados();
	
}
