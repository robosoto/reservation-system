package com.goeazycarrent.service.services;

import java.util.List;

import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Vehicles;

public interface VehicleService {
	public List<Vehicles> getAllVehicles() throws GoEazyException;

	public List<Vehicles> getVehicleByType(String type) throws GoEazyException;
	
	public List<Vehicles> getVehicleByLocation(String location) throws GoEazyException;

}
