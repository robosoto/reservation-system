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
import com.goeazycarrent.service.email.Mail;
import com.goeazycarrent.service.email.MailService;
import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Reservations;
import com.goeazycarrent.service.services.ReservationService;

@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins = { "http://localhost:4200" ,"http://goeazystore.s3-website-us-east-1.amazonaws.com"})
@Transactional
public class ReservationServiceController {

	@Autowired
	ReservationService reservationService;
	@Autowired
	MailService mailService;

	@GetMapping("/id/{id}")
	public ResponseEntity<Reservations> getReservationById(@PathVariable String id) throws GoEazyException {
		Reservations reservation;
		try {
			reservation = reservationService.getReservationById(id);

		} catch (GoEazyException e) {
			reservation = null;
			throw new GoEazyException("Failed to Retrieve Data");
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
		List<Integer> vehicles;
		vehicles = reservationService.getAllVehiclesByDate(reservationRequestdto.getLocation(), reservationRequestdto.getPickupDate(), reservationRequestdto.getDropoffDate());
		
		if(vehicles!=null && vehicles.contains(reservationRequestdto.getVehicleId())) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		
		
		Reservations saveReservation = reservationService.confirmReservation(reservationRequestdto);
		if(saveReservation!=null && saveReservation.getReservationId()!=null) {
			Mail mail = new Mail();
			mail.setMailFrom("sender@gmail.com");
			mail.setMailTo(reservationRequestdto.getEmail());
			mail.setMailSubject("GoEazyCarRental Rental Confirmation #"+ saveReservation.getReservationId());
			mail.setMailContent("Congratulations, you have successfully reserved a vehicle. Your confirmation ID is: "
					+ saveReservation.getReservationId());
			mailService.sendEmail(mail);
			return new ResponseEntity<>(saveReservation, HttpStatus.CREATED);
		}else {
			throw new GoEazyException("Reservation Failed");
		}

		

	}

	/**
	 * Cancel Reservation from id
	 * 
	 * @param ReservationRequestDto
	 * @return
	 * @throws GoEazyException
	 */
	@PutMapping("/cancel")
	public ResponseEntity<String> cancelReservation(@RequestBody ReservationRequestDto reservationRequestDto)
			throws GoEazyException {
		String reservationId = reservationRequestDto.getReservationId();
		reservationService.cancelReservation(reservationId);
		return new ResponseEntity<>("", HttpStatus.OK);

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
		if(saveReservation!=null && saveReservation.getReservationId()!=null) {
			Mail mail = new Mail();
			mail.setMailFrom("sender@gmail.com");
			mail.setMailTo(reservationRequestdto.getEmail());
			mail.setMailSubject("GoEazyCarRental Rental Confirmation #"+ saveReservation.getReservationId());
			mail.setMailContent("Congratulations, you have successfully updated your rental reservation. Your confirmation ID is: "
					+ saveReservation.getReservationId());
			mailService.sendEmail(mail);
			return new ResponseEntity<>(saveReservation, HttpStatus.OK);
		}else {
			throw new GoEazyException("Update Failed");
		}
		

	}

	/**
	 * Get vehicle IDs from all existing reservations given a location and date range
	 * 
	 * @param location
	 * @param fromDate
	 * @param toDate
	 * 
	 * @return list of reserved vehicle IDs
	 * 
	 * @throws GoEazyException
	 */
	@GetMapping("{location}/{fromDate}/{toDate}")
	public List<Integer> getReservedVehiclesByLocationAndDateRange(@PathVariable String location, @PathVariable String fromDate,
			@PathVariable String toDate) throws GoEazyException {
		List<Integer> vehicles;
		try {
			vehicles = reservationService.getAllVehiclesByDate(location, fromDate, toDate);

		} catch (GoEazyException e) {
			vehicles = null;
			throw new GoEazyException("Failed to Retrieve Data");
		}

		return vehicles;
	}

}
