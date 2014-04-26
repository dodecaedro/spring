package com.dodecaedro.transferservice.controller;

import com.dodecaedro.transferservice.data.pojo.Customer;
import com.dodecaedro.transferservice.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juan on 05/04/14.
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {
  @Inject
  private CustomerRepository customerRepository;

  @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = {"application/xml", "application/json"})
  @ResponseBody
  public Customer getCustomerByCustomerId(@PathVariable Integer customerId) {
    return customerRepository.findByCustomerId(customerId);
  }

  @RequestMapping(method = RequestMethod.GET, produces = {"application/xml", "application/json"})
  @ResponseBody
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable("id") Integer id) {
    this.customerRepository.delete(id);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateCustomer(@PathVariable("id") Integer id, @RequestBody Customer customer) {
    customer.setCustomerId(id);
    this.customerRepository.save(customer);
  }
}
