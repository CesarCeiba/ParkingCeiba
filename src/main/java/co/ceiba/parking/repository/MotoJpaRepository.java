package co.ceiba.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.parking.logica.Moto;

public interface MotoJpaRepository extends JpaRepository<Moto, String> {
 
}
