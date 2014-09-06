package com.dodecaedro.transferservice.controller;

import com.dodecaedro.transferservice.data.pojo.Customer;
import com.dodecaedro.transferservice.repository.CustomerRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by juan on 05/04/14.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {
  @Inject
  private CustomerRepository customerRepository;

  @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = {"application/xml", "application/json"})
  public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable Integer customerId) {
    try {
      Customer customer = customerRepository.findOne(customerId);
      return new ResponseEntity<>(customer, HttpStatus.OK);
    } catch (EntityNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.GET, produces = {"application/xml", "application/json"})
  @ResponseStatus(HttpStatus.OK)
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

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer, UriComponentsBuilder builder) {
    customerRepository.save(customer);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
            builder.path("/customers/{id}")
                    .buildAndExpand(customer.getCustomerId().toString()).toUri());

    return new ResponseEntity<>(customer, headers, HttpStatus.CREATED);
  }
}
