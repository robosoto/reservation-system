package com.goeazycarrent.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.goeazycarrent.service.model.Vehicles;
import com.goeazycarrent.service.repository.VehicleRepository;
import com.goeazycarrent.service.services.VehicleServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {
	
	@InjectMocks
	private VehicleServiceImpl vehicleService;
	
	
	@Mock
	private static VehicleRepository vehicleRepository;
	
	@Test
	public void getAllVehiclesTest() throws Exception {
		List<Vehicles> vehicleList=new ArrayList<>();
		when(vehicleRepository.findAll()).thenReturn(vehicleList);
		List<Vehicles> allVehicles = vehicleService.getAllVehicles();
		assertEquals(allVehicles, vehicleList);
	}
	@Test
	public void  getVehicleByTypeTest() throws Exception {
		
		List<Vehicles> vehicleList=new ArrayList<>();
		when(vehicleRepository.findAllByType("aa")).thenReturn(vehicleList);
		List<Vehicles> allVehicles = vehicleService.getVehicleByType("aa");
		assertEquals(allVehicles, vehicleList);
	}
	@Test
	public void  getVehicleByLocationTest() throws Exception {
		List<Vehicles> vehicleList=new ArrayList<>();
		when(vehicleRepository.findAllByLocation("aa")).thenReturn(vehicleList);
		List<Vehicles> allVehicles = vehicleService.getVehicleByLocation("aa");
		assertEquals(allVehicles, vehicleList);
		
	}
	@Test
	public void  getVehicleByLocationAndTypeTest() throws Exception {
		List<Vehicles> vehicleList=new ArrayList<>();
		when(vehicleRepository.findAllByLocationAndType("aa","bb")).thenReturn(vehicleList);
		List<Vehicles> allVehicles = vehicleService.getVehicleByLocationAndType("aa","bb");
		assertEquals(allVehicles, vehicleList);
		
	}
	
		

}
