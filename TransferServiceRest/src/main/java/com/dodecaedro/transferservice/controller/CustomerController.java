package com.dodecaedro.transferservice.controller;

import com.dodecaedro.transferservice.data.pojo.Customer;
import com.dodecaedro.transferservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
	@ResponseBody
	public Customer getCustomerByCustomerId(@PathVariable Integer customerId) {
		return customerRepository.findByCustomerId(customerId);
	}
	

}
