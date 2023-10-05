package com.goeazycarrent.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.goeazycarrent.service.dto.CustomerServiceRequestDto;
import com.goeazycarrent.service.model.Customers;
import com.goeazycarrent.service.repository.CustomerRepository;
import com.goeazycarrent.service.services.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@Mock
	private static CustomerRepository customerRepository;
	
	@Mock
	private static ModelMapper mapper;
	
	private static Customers testCustomer = new Customers();
	private String customerRequestJson = "{\"email\":\"test@mail.com\",\"name\":\"Test Name\"}";
	
	@BeforeAll
	public static void init() {
		testCustomer.setCustomerId(1);
		testCustomer.setEmail("customer@mail.com");
		testCustomer.setName("Test Name");
	}
	
	/**
	 * POST /customer/signup
	 */
	@Test
	public void saveCustomerDetails() throws Exception {
		when(mapper.map(any(CustomerServiceRequestDto.class),any())).thenReturn(testCustomer);
		
		// when customer already exists
		when(customerRepository.save(any(Customers.class))).thenReturn(testCustomer);
		
		CustomerServiceRequestDto dto=new CustomerServiceRequestDto();
		Customers saveCustomerDetails = customerService.saveCustomerDetails(dto);
		assertEquals(saveCustomerDetails, testCustomer);
	
	}
}

