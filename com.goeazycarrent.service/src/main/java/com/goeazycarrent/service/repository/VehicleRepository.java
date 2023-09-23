package com.goeazycarrent.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.goeazycarrent.service.model.Vehicles;

public interface VehicleRepository extends JpaRepository< Vehicles, Integer> {
	
	@Query(value = "select * from vehicles where type=:type", nativeQuery = true)
	List<Vehicles> findAllByType(@Param("type") String type);
	
	@Query(value = "select * from vehicles where location=:location", nativeQuery = true)
	List<Vehicles> findAllByLocation(@Param("location") String location);
	
	@Query(value = "select * from vehicles where " +
						"(:location IS NULL OR location=:location) " +
						"AND (:type IS NULL OR type=:type)",
					nativeQuery = true)
	List<Vehicles> findAllByLocationAndType(@Param("location") String location, 
											@Param("type") String type);
	
}

