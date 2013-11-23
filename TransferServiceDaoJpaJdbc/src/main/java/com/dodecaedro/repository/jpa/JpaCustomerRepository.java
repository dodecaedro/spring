package com.dodecaedro.repository.jpa;

import com.dodecaedro.data.pojo.Customer;
import com.dodecaedro.repository.CustomerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Repository
@Primary
public class JpaCustomerRepository implements CustomerRepository {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Customer findByCustomerId(Integer customerId) throws EntityNotFoundException {
    Customer customer = entityManager.find(Customer.class, customerId);
    if (customer == null) {
      throw new EntityNotFoundException("Customer with id: " + customerId + " not found.");
    }

    return customer;
  }

  @Transactional
  @Override
  public Customer save(Customer customer) {
      /* tested: if not marked as transactional, changes will not be
    	 * immediately written to the db, but will stay in memory only */
    return entityManager.merge(customer);
  }
}
