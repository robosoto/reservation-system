package com.goeazycarrent.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goeazycarrent.service.dto.CustomerServiceRequestDto;
import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Customers;
import com.goeazycarrent.service.services.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = {"http://localhost:4200"})
@Transactional
public class CustomerServiceController {

	@Autowired
	CustomerService customerService;
	
	@PostMapping("/signup")
	public ResponseEntity<Customers> storeCustomer(@RequestBody CustomerServiceRequestDto customerServiceRequestdto) throws GoEazyException{
		Customers saveCustomer = customerService.saveCustomerDetails(customerServiceRequestdto);
		return new ResponseEntity<>(saveCustomer, HttpStatus.OK);
		 
	}
}
