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
	
	@Test
	public void getAllReturnsGetAllVehicles() throws Exception {
		when(service.getAllVehicles()).thenReturn(mockVehicles);
		
		MvcResult result =  this.mvc.perform(get("/vehicle/all"))
								    .andDo(print())
								    .andExpect(status().isOk())
								    .andReturn();
		
		List<Vehicles> vehicles = mapper.readValue(result.getResponse().getContentAsString(),
												   new TypeReference<List<Vehicles>>() {});
		
		assertEquals(vehicles, mockVehicles);
	}
	

}
