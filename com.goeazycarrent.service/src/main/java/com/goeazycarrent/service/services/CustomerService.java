package com.goeazycarrent.service.services;

import com.goeazycarrent.service.dto.CustomerServiceRequestDto;
import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Customers;

public interface CustomerService {
	
	public Customers saveCustomerDetails(CustomerServiceRequestDto customerDto) throws GoEazyException;

}
