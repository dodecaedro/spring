package com.dodecaedro.repository;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import com.dodecaedro.data.pojo.Account;
import com.dodecaedro.data.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml", "/test-infraestructure-config.xml" })
public class JpaRepositoryIntegrationTest {

	@Resource
	private AccountRepository accountRepository;
	
	@Resource
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
