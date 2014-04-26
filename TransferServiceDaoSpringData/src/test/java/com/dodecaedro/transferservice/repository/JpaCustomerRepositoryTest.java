package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml", "/test-infrastructure-config.xml"})
public class JpaCustomerRepositoryTest {

  @Inject
  private CustomerRepository customerRepository;

  @Test
  @DirtiesContext
  public void saveCustomer() throws EntityNotFoundException {
    Customer customer = new Customer();
    customer.setFirstName("Iker");
    customer.setLastName("Casillas");
    customer.setPhoneNumber("555-546");
    customer.setAddress("Concha Espina, 5");

    customer = customerRepository.save(customer);

    assertNotNull(customerRepository.findOne(customer.getCustomerId()));
  }

  @Test
  public void loadCustomer() throws EntityNotFoundException {
    Customer customer = customerRepository.findOne(1);
    assertNotNull(customer);
    assertNotNull(customer.getAccount());
  }

  @Test
  public void testLoadAll() {
    List<Customer> customers = customerRepository.findAll();
    assertThat(customers.size(), is(4));
  }

  @Test
  @DirtiesContext
  public void testDelete() {
    customerRepository.delete(2);
    Customer nullCustomer = customerRepository.findOne(2);
    assertNull(nullCustomer);
  }
}
