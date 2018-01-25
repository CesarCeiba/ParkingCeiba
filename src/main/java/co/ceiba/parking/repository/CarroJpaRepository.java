package co.ceiba.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.parking.logica.Carro;

public interface CarroJpaRepository extends JpaRepository<Carro, String> {

}
