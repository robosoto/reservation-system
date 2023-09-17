package com.goeazycarrent.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.goeazycarrent.service.model.Reservations;

public interface ReservationRepository extends JpaRepository< Reservations, Integer>  {
	
	@Query(value = "select * from reservations where id=:id", nativeQuery = true)
	Reservations findByUniqueId(@Param("id") String id);
	
}
