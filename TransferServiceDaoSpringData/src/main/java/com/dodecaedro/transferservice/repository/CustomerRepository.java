package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.Customer;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Customer.class, idClass = Integer.class)
public interface CustomerRepository {
  Customer findOne(Integer customerId);
  Customer save(Customer customer);
  List<Customer> findAll();
  void delete(Integer customerId);
}
