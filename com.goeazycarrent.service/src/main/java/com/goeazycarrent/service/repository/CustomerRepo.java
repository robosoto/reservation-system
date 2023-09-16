package com.goeazycarrent.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goeazycarrent.service.model.Customers;

public class CustomerRepo {

	public interface CustomerRepository extends JpaRepository<Customers, Integer>  {

	}
}
