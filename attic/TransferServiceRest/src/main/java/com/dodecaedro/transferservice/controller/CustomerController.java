package com.dodecaedro.transferservice.controller;

import com.dodecaedro.transferservice.data.pojo.Customer;
import com.dodecaedro.transferservice.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Inject
	CustomerRepository customerRepository;

	@RequestMapping(value = "{customerId}", method = RequestMethod.GET)
	@ResponseBody
	public Customer getCustomerByCustomerId(@PathVariable Integer customerId) {
		return customerRepository.findByCustomerId(customerId);
	}


}
