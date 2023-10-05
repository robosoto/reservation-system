package com.goeazycarrent.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.goeazycarrent.service.dto.ReservationRequestDto;
import com.goeazycarrent.service.model.Reservations;
import com.goeazycarrent.service.repository.ReservationRepository;
import com.goeazycarrent.service.services.ReservationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

	@InjectMocks
	private ReservationServiceImpl reservationService;
	
	@Mock
	private static ReservationRepository reservationRepository;
	
	@Mock
	private static ModelMapper mapper;
	
	private static Reservations testReservation = new Reservations();
	private String customerRequestJson = "{\"email\":\"test@mail.com\",\"name\":\"Test Name\"}";
	
	@BeforeAll
	public static void init() {
		testReservation.setReservationId("1");
	}
	
	/**
	 * POST /customer/signup
	 */
	@Test
	public void getReservationIdTest() throws Exception {
		// when customer already exists
		when(reservationRepository.findByUniqueId(any(String.class))).thenReturn(testReservation);
		
	
		Reservations reservationById = reservationService.getReservationById("1");
		assertEquals(testReservation.getReservationId(), reservationById.getReservationId());
	
	}

	@Test
	public void getReservationIdExceptioTest() throws Exception {
		// when customer already exists
		when(reservationRepository.findByUniqueId(any(String.class))).thenReturn(null);
		
		try {
		 reservationService.getReservationById("1");
		}catch (Exception e) {
			
		}
	}
	
	@Test
	public void cancelRequestTest() throws Exception {
		// when customer already exists
		when(reservationRepository.findByUniqueId(any(String.class))).thenReturn(testReservation);
		when(reservationRepository.save(any(Reservations.class))).thenReturn(testReservation);
		 reservationService.cancelReservation("1");
		
	}
	
	@Test
	public void getVehicleByDateTest() throws Exception {
		List<Integer> vehicleList=new ArrayList<>();
		vehicleList.add(1);
		// when customer already exists
		when(reservationRepository.findVehiclesByDate(any(String.class), any(String.class), any(String.class))).thenReturn(vehicleList);
		List<Integer> allVehiclesByDate = reservationService.getAllVehiclesByDate("abac","nnn","jjj");
		assertEquals(allVehiclesByDate.get(0),vehicleList.get(0));
		
	}
	
	@Test
	public void modifyReservationTest() throws Exception {
		ReservationRequestDto dto=new ReservationRequestDto();
		dto.setReservationId("1");
		testReservation.setEndDate(new Date());
		testReservation.setStartEnd(new Date());
		testReservation.setVehicleId(5);
		when(reservationRepository.findByUniqueId(any(String.class))).thenReturn(testReservation);
		when(reservationRepository.save(any(Reservations.class))).thenReturn(testReservation);
		Reservations modifyReservation = reservationService.modifyReservation(dto);
		assertEquals(testReservation,modifyReservation);
		
	}
	
	@Test
	public void modifyConfirmReservationTest() throws Exception {
		ReservationRequestDto dto=new ReservationRequestDto();
		dto.setLocation("Cancun");
		dto.setDropoffDate("2023-10-04 17:16:38");
		dto.setPickupDate("2023-10-03 17:16:38");
		when(mapper.map(any(ReservationRequestDto.class),any())).thenReturn(testReservation);
		when(reservationRepository.save(any(Reservations.class))).thenReturn(testReservation);
		Reservations confirmReservation = reservationService.confirmReservation(dto);
		assertEquals(testReservation,confirmReservation);
		
	}
}

