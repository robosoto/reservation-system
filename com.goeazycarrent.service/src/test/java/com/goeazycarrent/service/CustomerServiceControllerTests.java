package com.goeazycarrent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.goeazycarrent.service.controller.CustomerServiceController;
import com.goeazycarrent.service.services.CustomerService;

@WebMvcTest(CustomerServiceController.class)
public class CustomerServiceControllerTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CustomerService service;
	
//	/**
//	 * POST /customer/signup
//	 */
//	public void customerSignup() throws Exception {
//		
//	}
}
