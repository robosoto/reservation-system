package com.goeazycarrent.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goeazycarrent.service.model.Vehicles;

public class VehicleRepo {
	
	public interface VehicleRepository extends JpaRepository< Vehicles, Integer> {
		
	}

}
