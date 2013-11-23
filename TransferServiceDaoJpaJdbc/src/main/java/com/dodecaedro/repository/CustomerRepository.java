package com.dodecaedro.repository;

import com.dodecaedro.data.pojo.Customer;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerRepository {
  Customer findByCustomerId(Integer customerId);
  Customer save(Customer customer);
}
