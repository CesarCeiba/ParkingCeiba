package co.ceiba.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.ceiba.parking.logica.Vehiculo;

public interface VehiculoJpaRepository extends JpaRepository<Vehiculo, String>{
	
	@Query(value = "SELECT count(1) FROM Vehiculo WHERE placa = ?1 And hora_salida is null")
	int vehiculoParqueado(String placa);
	
}
