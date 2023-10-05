package com.goeazycarrent.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goeazycarrent.service.controller.VehicleServiceController;
import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Vehicles;
import com.goeazycarrent.service.services.VehicleService;

@WebMvcTest(VehicleServiceController.class)
public class VehicleServiceControllerTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private VehicleService service;
	
	ObjectMapper mapper = new ObjectMapper();
	
	private static List<Vehicles> mockVehicles = new ArrayList<>();
	
	@BeforeAll
	public static void init() {		
		Vehicles v1 = new Vehicles();
		v1.setVehicleId(1);
		v1.setMake("Toyota");
		v1.setModel("Camry");
		v1.setType("Car");
		v1.setLocation("Philadelphia");
		v1.setPricePerDay(50);
		
		Vehicles v2 = new Vehicles();
		v2.setVehicleId(2);
		v2.setMake("Subaru");
		v2.setModel("Forester");
		v2.setType("SUV");
		v2.setLocation("Cancun");
		v2.setPricePerDay(75);
		
		mockVehicles.add(v1);
		mockVehicles.add(v2);
	}
	
	/**
	 * GET /vehicle/all
	 */
	@Test
	public void getAllVehicles() throws Exception {
		when(service.getAllVehicles()).thenReturn(mockVehicles);
		
		// check that route returns 200
		MvcResult result =  this.mvc.perform(get("/vehicle/all"))
								    .andExpect(status().isOk())
								    .andReturn();
		
		// check that response content is a list of vehicles
		List<Vehicles> vehicles = mapper.readValue(result.getResponse().getContentAsString(),
												   new TypeReference<List<Vehicles>>() {});
		
		assertEquals(mockVehicles, vehicles);
	}
	
	/**
	 * GET /vehicle/type/{type}
	 */
	@Test
	public void getVehicleByType() throws Exception {
		when(service.getVehicleByType("SUV")).thenReturn(mockVehicles);
		
		// check that route returns 200
		MvcResult result =  this.mvc.perform(get("/vehicle/type/" + "SUV"))
								    .andExpect(status().isOk())
								    .andReturn();
		
		// check that response content is a list of vehicles
		List<Vehicles> vehicles = mapper.readValue(result.getResponse().getContentAsString(),
									   				new TypeReference<List<Vehicles>>() {});
		
		assertEquals(mockVehicles, vehicles);
	}
	
	@Test
	public void getVehicleByTypeException() throws Exception {
		when(service.getVehicleByType("SUV")).thenThrow(GoEazyException.class);
		
		// check that route returns 200
		this.mvc.perform(get("/vehicle/type/" + "SUV"))
								    .andExpect(status().is5xxServerError());
		
	}
	
	/**
	 * GET /vehicle/location/{location}
	 */
	@Test
	public void getVehicleByLocation() throws Exception {
		when(service.getVehicleByLocation("Philadelphia")).thenReturn(mockVehicles);
		
		// check that route returns 200
		MvcResult result =  this.mvc.perform(get("/vehicle/location/" + "Philadelphia"))
								    .andExpect(status().isOk())
								    .andReturn();
		
		// check that response content is a list of vehicles
		List<Vehicles> vehicles = mapper.readValue(result.getResponse().getContentAsString(),
						   							new TypeReference<List<Vehicles>>() {});
		
		assertEquals(mockVehicles, vehicles);
	}
	
	@Test
	public void getVehicleByLocationException() throws Exception {
		when(service.getVehicleByLocation("Philadelphia")).thenThrow(GoEazyException.class);
		
		// check that route returns 200
		this.mvc.perform(get("/vehicle/location/" + "Philadelphia")).andExpect(status().is5xxServerError());
		
		
	}
	
	/**
	 * GET /vehicle/filter
	 */
	@Test
	public void getVehicleByLocationAndType() throws Exception {
		when(service.getVehicleByLocationAndType(null, null)).thenReturn(mockVehicles);
		when(service.getVehicleByLocationAndType(null, "Truck")).thenReturn(mockVehicles);
		when(service.getVehicleByLocationAndType("Philadelphia", null)).thenReturn(mockVehicles);
		when(service.getVehicleByLocationAndType("Philadelphia", "Truck")).thenReturn(mockVehicles);
		
		// check that route returns 200, both params null
		MvcResult result =  this.mvc.perform(get("/vehicle/filter"))
								    .andExpect(status().isOk())
								    .andReturn();
		
		// check that response content is a list of vehicles
		List<Vehicles> vehicles = mapper.readValue(result.getResponse().getContentAsString(),
						   							new TypeReference<List<Vehicles>>() {});
		
		assertEquals(mockVehicles, vehicles);
		
		// check that route returns 200, location null
		result =  this.mvc.perform(get("/vehicle/filter?vehicleType=Truck"))
								    .andExpect(status().isOk())
								    .andReturn();
		
		// check that response content is a list of vehicles
		vehicles = mapper.readValue(result.getResponse().getContentAsString(),
						   							new TypeReference<List<Vehicles>>() {});
		
		assertEquals(mockVehicles, vehicles);
		
		// check that route returns 200, vehicleType null
		result =  this.mvc.perform(get("/vehicle/filter?location=Philadelphia"))
								    .andExpect(status().isOk())
								    .andReturn();
		
		// check that response content is a list of vehicles
		vehicles = mapper.readValue(result.getResponse().getContentAsString(),
						   							new TypeReference<List<Vehicles>>() {});
		
		assertEquals(mockVehicles, vehicles);
		
		// check that route returns 200, both location and vehicleType non null
		result =  this.mvc.perform(get("/vehicle/filter?location=Philadelphia&vehicleType=Truck"))
								    .andExpect(status().isOk())
								    .andReturn();
		
		// check that response content is a list of vehicles
		vehicles = mapper.readValue(result.getResponse().getContentAsString(),
						   							new TypeReference<List<Vehicles>>() {});
		
		assertEquals(mockVehicles, vehicles);
	}

}
