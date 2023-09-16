package com.goeazycarrent.service.services;

import java.util.List;

import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Vehicles;

public interface VehicleService {
	public List<Vehicles> getAllVehicles() throws GoEazyException;

	public Vehicles getVehicleByType(String type) throws GoEazyException;
	
	public Vehicles getVehicleByLocation(String location) throws GoEazyException;

}
