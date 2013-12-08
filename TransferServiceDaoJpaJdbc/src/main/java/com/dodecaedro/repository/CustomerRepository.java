package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.Customer;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerRepository {
  Customer findByCustomerId(Integer customerId);
  Customer save(Customer customer);
}
