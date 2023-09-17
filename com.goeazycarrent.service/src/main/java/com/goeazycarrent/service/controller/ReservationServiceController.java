package com.goeazycarrent.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goeazycarrent.service.dto.ReservationRequestDto;
import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Reservations;
import com.goeazycarrent.service.services.ReservationService;

@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins = { "http://localhost:4200" })
@Transactional
public class ReservationServiceController {

	@Autowired
	ReservationService reservationService;

	@GetMapping("/id/{id}")
	public ResponseEntity<Reservations> getReservationById(@PathVariable String id) throws GoEazyException {
		Reservations reservation;
		try {
			reservation = reservationService.getReservationById(id);

		} catch (GoEazyException e) {
			reservation = null;
		}
		return new ResponseEntity<Reservations>(reservation, HttpStatus.OK);

	}

	/**
	 * Save the reservation details in database
	 * 
	 * @param reservationRequestdto
	 * @return
	 * @throws GoEazyException
	 */
	@PostMapping("/confirm")
	public ResponseEntity<Reservations> confirmReservation(@RequestBody ReservationRequestDto reservationRequestdto)
			throws GoEazyException {
		Reservations saveReservation = reservationService.confirmReservation(reservationRequestdto);
		return new ResponseEntity<>(saveReservation, HttpStatus.OK);

	}

	/**
	 * Cancel Reservation from id
	 * 
	 * @param id
	 * @throws GoEazyException
	 */
	@PutMapping("/cancel/{id}")
	public void cancelReservationById(@PathVariable String id) throws GoEazyException {
		reservationService.cancelReservation(id);

	}

	/**
	 * Modify the Reservation details
	 * 
	 * @param ReservationRequestdto
	 * @return
	 * @throws GoEazyException
	 */

	@PutMapping("/modify")
	public ResponseEntity<Reservations> modifyReservation(@RequestBody ReservationRequestDto reservationRequestdto)
			throws GoEazyException {
		Reservations saveReservation = reservationService.modifyReservation(reservationRequestdto);
		return new ResponseEntity<>(saveReservation, HttpStatus.OK);

	}

	@GetMapping("{location}/{fromDate}/{toDate}")
	public List<Integer> getReservationById(@PathVariable String location,@PathVariable String fromDate, @PathVariable String toDate)
			throws GoEazyException {
		List<Integer> vehicles;
		try {
			vehicles = reservationService.getAllVehiclesByDate(location,fromDate, toDate);

		} catch (GoEazyException e) {
			vehicles = null;
		}
	
		return vehicles;
	}

}
