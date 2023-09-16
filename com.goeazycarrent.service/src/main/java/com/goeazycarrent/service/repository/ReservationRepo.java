package com.goeazycarrent.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goeazycarrent.service.model.Reservations;

public class ReservationRepo {
	public interface ReservationRepository extends JpaRepository< Reservations, Integer>  {

	}

}
