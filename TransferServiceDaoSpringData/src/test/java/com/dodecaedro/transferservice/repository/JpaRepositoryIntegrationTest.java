package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.Account;
import com.dodecaedro.transferservice.data.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml", "/test-infraestructure-config.xml"})
public class JpaRepositoryIntegrationTest {

  @Inject
  @Named("accountRepository")
  private AccountRepository accountRepository;

  @Inject
  private CustomerRepository customerRepository;

  @Test
  public void saveCustomerAndAccount() throws EntityNotFoundException {
    Customer customer = new Customer();
    customer.setFirstName("Gonzalo");
    customer.setLastName("Higuain");
    customer.setAddress("Concha Espina, 6");
    customer = customerRepository.save(customer);

    Account account = new Account();
    account.credit(100);
    account.setCustomer(customer);
    accountRepository.save(account);

    Customer customerAfterSave = customerRepository.findByCustomerId(customer.getCustomerId());

    assertNotNull(customerAfterSave);
    assertNotNull(customerAfterSave.getAccount());
  }
}
