package com.goeazycarrent.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.goeazycarrent.service.controller.CustomerServiceController;
import com.goeazycarrent.service.dto.CustomerServiceRequestDto;
import com.goeazycarrent.service.exception.GoEazyException;
import com.goeazycarrent.service.model.Customers;
import com.goeazycarrent.service.services.CustomerService;

@WebMvcTest(CustomerServiceController.class)
public class CustomerServiceControllerTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CustomerService customerService;
	
	private static Customers testCustomer = new Customers();
	private static Customers testNullCustomer = new Customers();
	private String customerRequestJson = "{\"email\":\"test@mail.com\",\"name\":\"Test Name\"}";
	private String customerRequestJsonWithMissingEmail = "{\"email\":\"\",\"name\":\"Test Name\"}";
	
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
	public void customerSignup() throws Exception {
		when(customerService.saveCustomerDetails(any(CustomerServiceRequestDto.class))).thenReturn(testCustomer);
		
		// when customer already exists
		when(customerService.findCustomerDetails("test@mail.com")).thenReturn(1);
		
		RequestBuilder request = MockMvcRequestBuilders.post("/customer/signup")
													   .accept(MediaType.APPLICATION_JSON)
													   .content(customerRequestJson)
													   .contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		// when customer does not exist yet
		when(customerService.findCustomerDetails("test@mail.com")).thenReturn(null);
		
		request = MockMvcRequestBuilders.post("/customer/signup")
									    .accept(MediaType.APPLICATION_JSON)
									    .content(customerRequestJson)
									    .contentType(MediaType.APPLICATION_JSON);

		result = mvc.perform(request).andReturn();
		response = result.getResponse();
		
		// expect response status of 201 CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void customerSignupWithException() throws Exception {
		when(customerService.saveCustomerDetails(any(CustomerServiceRequestDto.class))).thenThrow(GoEazyException.class);
		
		// when customer already exists
		when(customerService.findCustomerDetails("test@mail.com")).thenReturn(1);
		
		RequestBuilder request = MockMvcRequestBuilders.post("/customer/signup")
													   .accept(MediaType.APPLICATION_JSON)
													   .content(customerRequestJsonWithMissingEmail)
													   .contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(500, response.getStatus());
	
	}
}
