package com.goeazycarrent.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Vehicles;
import com.goeazycarrent.service.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements  VehicleService{

	@Autowired
	VehicleRepository vehicleRepository;
	
	@Override
	public List<Vehicles> getAllVehicles() throws GoEazyException {
		return vehicleRepository.findAll();
	}

	@Override
	public List<Vehicles> getVehicleByType(String type) throws GoEazyException {
		List<Vehicles> findAllByType = vehicleRepository.findAllByType(type);
		return findAllByType;
	}

	@Override
	public List<Vehicles> getVehicleByLocation(String location) throws GoEazyException {
		List<Vehicles> findAllByLocation = vehicleRepository.findAllByLocation(location);
		return findAllByLocation;
	}
	
	

}
