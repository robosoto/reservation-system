package com.goeazycarrent.service.services;

import java.util.List;

import com.goeazycarrent.service.dto.ReservationRequestDto;
import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Reservations;



public interface ReservationService {
	

	//public List<Reservations> getAllReservation() throws GoEazyException;

	public Reservations getReservationById(String reservationId) throws GoEazyException;

	public void cancelReservation(String id) throws GoEazyException;
	
	public Reservations modifyReservation(ReservationRequestDto reservationDto) throws GoEazyException;
	
	public Reservations confirmReservation(ReservationRequestDto reservationDto) throws GoEazyException;
	
}
