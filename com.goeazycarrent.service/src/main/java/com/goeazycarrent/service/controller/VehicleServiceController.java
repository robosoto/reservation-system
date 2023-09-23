package com.goeazycarrent.service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Vehicles;
import com.goeazycarrent.service.services.VehicleService;

/**
 * Controller for Handling CRUD operation for vehicles
 */
@RestController
@RequestMapping("/vehicle")
// TODO: change origin to our AWS frontend host
@CrossOrigin(origins = { "http://localhost:4200" })
@Transactional
public class VehicleServiceController {
	@Autowired
	VehicleService vehicleService;

	/**
	 * Get vehicle by type
	 * 
	 * @param type
	 * @return
	 */
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Vehicles>> getVehicleByType(@PathVariable String type) throws GoEazyException {
		List<Vehicles> vehicle;
		try {
			vehicle = vehicleService.getVehicleByType(type);

		} catch (GoEazyException e) {
			vehicle = null;
		}
		return new ResponseEntity<List<Vehicles>>(vehicle, HttpStatus.OK);

	}

	/**
	 * Get All vehicles
	 * 
	 * @return
	 * @throws GoEazyException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Vehicles>> getAllvehicle() throws GoEazyException {
		List<Vehicles> allVehicleList = new ArrayList<>();
		allVehicleList = vehicleService.getAllVehicles();
		return new ResponseEntity<List<Vehicles>>(allVehicleList, HttpStatus.OK);

	}

	/**
	 * Get vehicle by Location
	 * 
	 * @param location
	 * @return
	 */
	@GetMapping("/location/{location}")
	public ResponseEntity<List<Vehicles>> getVehicleByLocation(@PathVariable String location) throws GoEazyException {
		List<Vehicles> vehiclesByLocation;
		try {
			vehiclesByLocation = vehicleService.getVehicleByLocation(location);

		} catch (GoEazyException e) {
			vehiclesByLocation = null;
		}
		return new ResponseEntity<List<Vehicles>>(vehiclesByLocation, HttpStatus.OK);

	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<Vehicles>> getVehicleByLocationAndType(
			@RequestParam(required = false) String location,
			@RequestParam(required = false) String vehicleType) throws GoEazyException 
	{
		List<Vehicles> vehiclesByLocationAndType;
		
		try {
			vehiclesByLocationAndType = vehicleService.getVehicleByLocationAndType(location, vehicleType);

		} catch (GoEazyException e) {
			vehiclesByLocationAndType = null;
		}
		return new ResponseEntity<List<Vehicles>>(vehiclesByLocationAndType, HttpStatus.OK);
	}

}
