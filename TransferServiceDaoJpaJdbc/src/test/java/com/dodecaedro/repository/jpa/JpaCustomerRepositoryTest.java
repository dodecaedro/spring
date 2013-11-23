package com.dodecaedro.repository.jpa;

import com.dodecaedro.data.pojo.Customer;
import com.dodecaedro.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml", "/test-infraestructure-config.xml"})
public class JpaCustomerRepositoryTest {

  @Resource
  private CustomerRepository customerRepository;

  @Test
  public void saveCustomer() throws EntityNotFoundException {
    Customer customer = new Customer();
    customer.setFirstName("Iker");
    customer.setLastName("Casillas");
    customer.setPhoneNumber("555-546");
    customer.setAddress("Concha Espina, 5");

    customer = customerRepository.save(customer);

    assertNotNull(customerRepository.findByCustomerId(customer.getCustomerId()));
  }

  @Test
  public void loadCustomer() throws EntityNotFoundException {
    Customer customer = customerRepository.findByCustomerId(1);
    assertNotNull(customer);
    assertNotNull(customer.getAccount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullCustomerSave() {
    Customer customer = null;
    customerRepository.save(customer);
  }

}
