package com.goeazycarrent.service.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goeazycarrent.service.dto.CustomerServiceRequestDto;
import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Customers;
import com.goeazycarrent.service.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements  CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Customers saveCustomerDetails(CustomerServiceRequestDto customerDto) throws GoEazyException {
		Customers customerMap = mapper.map(customerDto, Customers.class);
		return customerRepository.save(customerMap);
		
	}

}
