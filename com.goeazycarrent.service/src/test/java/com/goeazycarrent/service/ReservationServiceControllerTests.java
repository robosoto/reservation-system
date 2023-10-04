package com.goeazycarrent.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goeazycarrent.service.controller.ReservationServiceController;
import com.goeazycarrent.service.dto.ReservationRequestDto;
import com.goeazycarrent.service.email.MailService;
import com.goeazycarrent.service.model.Reservations;
import com.goeazycarrent.service.model.Vehicles;
import com.goeazycarrent.service.services.ReservationService;

@WebMvcTest(ReservationServiceController.class)
public class ReservationServiceControllerTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ReservationService resService;
	@MockBean
	private MailService mailService;

	private ObjectMapper mapper = new ObjectMapper();
	
	private static Reservations r1 = new Reservations();
	private static Reservations r2 = new Reservations();
	private static Reservations newRes = new Reservations();
	private static List<Reservations> mockReservations = new ArrayList<>();
	
	private String reservationRequestJson = "{\"customerId\":6,\"vehicleId\":2,\"dropoffDate\":\"2023-10-31 20:35:48\"," +
			"\"pickupDate\":\"2023-10-30 20:35:48\",\"location\":\"Philadelphia\"," +
			"\"email\":\"test@mail.com\"}";
	private String takenReservationRequestJson = "{\"customerId\":6,\"vehicleId\":1,\"dropoffDate\":\"2023-10-31 20:35:48\"," +
			"\"pickupDate\":\"2023-10-30 20:35:48\",\"location\":\"Philadelphia\"," +
			"\"email\":\"test@mail.com\"}";
	private String cancelReservationRequestJson = "{\"reservationId\":\"1a2b3c\"}";
	
	private static List<Integer> reservedVehicleIds = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	public static void init() {
		r1.setReservationId("1a2b3c");
		r1.setCustomerId(1);
		r1.setVehicleId(1);
		r1.setLocation("Philadelphia");
		r1.setStatus("Reserved");
		r1.setStartEnd(new Date(2023, 11, 11, 11, 11, 11));
		r1.setEndDate(new Date(2023, 11, 21, 11, 11, 11));
		
		r2.setReservationId("2a3b4c");
		r2.setCustomerId(2);
		r2.setVehicleId(2);
		r2.setLocation("Cancun");
		r2.setStatus("Reserved");
		r2.setStartEnd(new Date(2023, 12, 11, 11, 11, 11));
		r2.setEndDate(new Date(2023, 12, 22, 11, 11, 11));
		
		mockReservations.add(r1);
		mockReservations.add(r2);
		
		reservedVehicleIds.add(1);
		reservedVehicleIds.add(3);
		
		newRes.setReservationId(null);
	}
	
	/**
	 * GET /reservation/id/{id}
	 */
	@Test
	public void getReservationById() throws Exception {
		when(resService.getReservationById("1a2b3c")).thenReturn(r1);
		
		// check that route returns 200
		MvcResult result =  this.mvc.perform(get("/reservation/id/1a2b3c"))
								    .andExpect(status().isOk())
								    .andReturn();
		
		// check that response content is a single reservation
		Reservations res = mapper.readValue(result.getResponse().getContentAsString(),
											 new TypeReference<Reservations>() {});
		
		assertEquals(r1, res);
	}
	
	/**
	 * POST /reservation/confirm
	 */
	@Test
	public void postReservationConfirm() throws Exception {
		when(resService.confirmReservation(any(ReservationRequestDto.class))).thenReturn(r1);
		when(resService.getAllVehiclesByDate("Philadelphia", "2023-10-30 20:35:48", "2023-10-31 20:35:48"))
					   .thenReturn(reservedVehicleIds);
		
		// Request a vehicle that is not yet reserved
		RequestBuilder request = MockMvcRequestBuilders.post("/reservation/confirm")
													   .accept(MediaType.APPLICATION_JSON)
													   .content(reservationRequestJson)
													   .contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		// expect response status of 201 CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		// check that response content is a Reservation
		Reservations reservation = mapper.readValue(response.getContentAsString(),
									   				new TypeReference<Reservations>() {});
		assertEquals(r1, reservation);
		
		// Request a vehicle that is already reserved, expect failure
		request = MockMvcRequestBuilders.post("/reservation/confirm")
									    .accept(MediaType.APPLICATION_JSON)
									    .content(takenReservationRequestJson)
									    .contentType(MediaType.APPLICATION_JSON);
		
		result = mvc.perform(request).andReturn();
		response = result.getResponse();
		
		// expect response status of 409 CONFLICT
		assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
	}
	
	/**
	 * PUT /reservation/cancel
	 */
	@Test
	public void putReservationCancel() throws Exception {
		doNothing().when(resService).cancelReservation(any(String.class));
		
		RequestBuilder request = MockMvcRequestBuilders.put("/reservation/cancel")
													   .accept(MediaType.APPLICATION_JSON)
													   .content(cancelReservationRequestJson)
													   .contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		// expect response status of 200 OK
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		// expect response content to be empty string
		assertEquals("", response.getContentAsString());
	}
	
	/**
	 * PUT /reservation/modify
	 */
	@Test
	public void putReservationModify() throws Exception {
		when(resService.modifyReservation(any(ReservationRequestDto.class))).thenReturn(r1);
		
		RequestBuilder request = MockMvcRequestBuilders.put("/reservation/modify")
													   .accept(MediaType.APPLICATION_JSON)
													   .content(reservationRequestJson)
													   .contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		// expect response status of 200 OK
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		// check that response content is a Reservation
		Reservations reservation = mapper.readValue(response.getContentAsString(),
									   				new TypeReference<Reservations>() {});
		assertEquals(r1, reservation);
	}
	
	/**
	 * GET /reservation/{location}/{fromDate}/{toDate}
	 */
	@Test
	public void getReservedVehiclesByLocationAndDateRange() throws Exception {
		when(resService.getAllVehiclesByDate(any(String.class), any(String.class), any(String.class)))
					   .thenReturn(reservedVehicleIds);
		
		// check that route returns 200
		MvcResult result =  this.mvc.perform(get("/reservation/philadelphia/2023-11-12 00:00:00/2023-11-15 00:00:00"))
								    .andExpect(status().isOk())
								    .andReturn();
		
		// check that response content is a list of vehicle IDs
		List<Integer> vehicleIds = mapper.readValue(result.getResponse().getContentAsString(),
									   				new TypeReference<List<Integer>>() {});
		
		assertEquals(reservedVehicleIds, vehicleIds);
	}
	
}
