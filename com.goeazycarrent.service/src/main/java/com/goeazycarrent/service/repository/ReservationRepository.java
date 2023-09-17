package com.goeazycarrent.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goeazycarrent.service.model.Reservations;

public interface ReservationRepository extends JpaRepository< Reservations, Integer>  {

}
