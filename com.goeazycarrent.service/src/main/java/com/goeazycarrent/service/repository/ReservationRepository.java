package com.goeazycarrent.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.goeazycarrent.service.model.Reservations;

public interface ReservationRepository extends JpaRepository< Reservations, Integer>  {
	
	@Query(value = "select * from reservations where id=:id", nativeQuery = true)
	Reservations findByUniqueId(@Param("id") String id);
	
	@Query(value = "select vehicle_id from reservations where start_date >= :fromDate AND end_date <=:toDate and status='Reserved' and location=:location", nativeQuery = true)
	List<Integer> findVehiclesByDate(@Param("location") String location,@Param("fromDate") String fromDate,@Param("toDate") String toDate);
	
}
