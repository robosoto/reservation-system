package com.goeazycarrent.service.controller;

import java.util.ArrayList;
import java.util.List;

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
@CrossOrigin(origins = { "http://localhost:4200","http://goeazystore.s3-website-us-east-1.amazonaws.com" })
@Transactional
public class VehicleServiceController {
	@Autowired
	VehicleService vehicleService;

	/**
	 * Get vehicles by type
	 * 
	 * @param String type
	 * 
	 * @return HTTP 200 response with list of vehicles of requested type in response body
	 */
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Vehicles>> getVehicleByType(@PathVariable String type) throws GoEazyException {
		List<Vehicles> vehicle;
		try {
			vehicle = vehicleService.getVehicleByType(type);
			return new ResponseEntity<List<Vehicles>>(vehicle, HttpStatus.OK);
		} catch (GoEazyException e) {
			throw new GoEazyException("Failed to Retrieve Vehicles by Type");
		}
	

	}

	/**
	 * Get All vehicles
	 * 
	 * @return HTTP 200 response with list of all vehicles in response body
	 * 
	 * @throws GoEazyException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Vehicles>> getAllvehicle() throws GoEazyException {
		List<Vehicles> allVehicleList = new ArrayList<>();
		allVehicleList = vehicleService.getAllVehicles();
		return new ResponseEntity<List<Vehicles>>(allVehicleList, HttpStatus.OK);

	}

	/**
	 * Get vehicles by Location
	 * 
	 * @param String location
	 * 
	 * @return HTTP 200 response with list of vehicles of requested location in response body
	 */
	@GetMapping("/location/{location}")
	public ResponseEntity<List<Vehicles>> getVehicleByLocation(@PathVariable String location) throws GoEazyException {
		List<Vehicles> vehiclesByLocation;
		try {
			vehiclesByLocation = vehicleService.getVehicleByLocation(location);
			return new ResponseEntity<List<Vehicles>>(vehiclesByLocation, HttpStatus.OK);
		} catch (GoEazyException e) {
			throw new GoEazyException("Failed to Retrieve Vehicles by Location");
		}
		

	}
	
	/**
	 * Get vehicles by a combination of location and type. 
	 * Either location or type may be null.
	 * 
	 * @param (String or null) location
	 * @param (String or null) vehicleType
	 * 
	 * @return HTTP 200 response with list of vehicles of requested 
	 * 		   type & location in response body
	 * 
	 * @throws GoEazyException
	 */
	@GetMapping("/filter")
	public ResponseEntity<List<Vehicles>> getVehicleByLocationAndType(
			@RequestParam(required = false) String location,
			@RequestParam(required = false) String vehicleType) throws GoEazyException 
	{
		List<Vehicles> vehiclesByLocationAndType;
		
		try {
			vehiclesByLocationAndType = vehicleService.getVehicleByLocationAndType(location, vehicleType);
			return new ResponseEntity<List<Vehicles>>(vehiclesByLocationAndType, HttpStatus.OK);
		} catch (GoEazyException e) {
			throw new GoEazyException("Failed to Retrieve Vehicles by Location and Type");
		}
	
	}

}
