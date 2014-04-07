package com.dodecaedro.transferservice.controller;

import com.dodecaedro.transferservice.data.pojo.Customer;
import com.dodecaedro.transferservice.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juan on 05/04/14.
 */
@Controller
public class CustomerController {
  @Inject
  private CustomerRepository customerRepository;

  @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET, produces = {"application/xml", "application/json"})
  @ResponseBody
  public Customer getCustomerByCustomerId(@PathVariable Integer customerId) {
    return customerRepository.findByCustomerId(customerId);
  }

  @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = {"application/xml", "application/json"})
  @ResponseBody
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }
}
