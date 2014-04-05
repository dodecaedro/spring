package com.dodecaedro.transferservice.controller;

import com.dodecaedro.transferservice.data.pojo.Customer;
import com.dodecaedro.transferservice.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by juan on 05/04/14.
 */
@Controller
@RequestMapping("/customer")
public class CustomersController {
  @Inject
  private CustomerRepository customerRepository;

  @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
  @ResponseBody
  public Customer getCustomerByCustomerId(@PathVariable Integer customerId) {
    return customerRepository.findByCustomerId(customerId);
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public String getAllCustomers(Model model) {
    model.addAttribute("customers", customerRepository.findAll());
    return "customers-list";
  }

}
