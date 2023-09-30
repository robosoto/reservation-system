package com.goeazycarrent.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.goeazycarrent.service.model.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Integer>  {

	@Query(value = "select id from customers where email=:email", nativeQuery = true)
	Integer findByUniqueEmail(@Param("email") String email);
	
}
