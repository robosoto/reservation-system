package com.goeazycarrent.service.controller;

import org.apache.commons.lang3.StringUtils;
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
@CrossOrigin(origins = {"http://localhost:4200", "http://goeazystore.s3-website-us-east-1.amazonaws.com"})
@Transactional
public class CustomerServiceController {

	@Autowired
	CustomerService customerService;
	
	@PostMapping("/signup")
	public ResponseEntity<Integer> storeCustomer(@RequestBody CustomerServiceRequestDto customerServiceRequestdto) throws GoEazyException{
		if(StringUtils.isNotBlank(customerServiceRequestdto.getEmail())) {
			Integer findCustomerDetails = customerService.findCustomerDetails(StringUtils.trim(customerServiceRequestdto.getEmail()));
			if(findCustomerDetails!=null && findCustomerDetails > 0) {
				return new ResponseEntity<>(findCustomerDetails, HttpStatus.OK);
			}else {
				customerServiceRequestdto.setEmail(StringUtils.trim(customerServiceRequestdto.getEmail()));
				customerServiceRequestdto.setName(StringUtils.trim(customerServiceRequestdto.getName()));
				Customers saveCustomer = customerService.saveCustomerDetails(customerServiceRequestdto);
				if(saveCustomer !=null && saveCustomer.getCustomerId()!=null) {
					return new ResponseEntity<>(saveCustomer.getCustomerId(), HttpStatus.OK);
				}else {
					throw new GoEazyException("Failed to Create Customer");
				}
			}
			
		}else {
			throw new GoEazyException("Email Id is missing");
		}
		 
	}
}
