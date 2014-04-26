package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.Account;
import com.dodecaedro.transferservice.data.pojo.CreditCard;
import com.dodecaedro.transferservice.data.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml", "/test-infrastructure-config.xml"})
public class JpaRepositoryIntegrationTest {

  @Inject
  @Named("accountRepository")
  private AccountRepository accountRepository;
  @Inject
  private CustomerRepository customerRepository;
  @Inject
  private CreditCardRepository creditCardRepository;

  @Test
  public void saveAccount() {
    Customer customer = createCustomer();
    customerRepository.save(customer);

    Account account = new Account();
    account.setCustomer(customer);
    account.credit(200);

    accountRepository.save(account);

    assertNotNull(account.getAccountId());
  }

  @Test
  public void saveCreditCard() {
    Customer customer = customerRepository.findOne(2);

    CreditCard creditCard = new CreditCard();
    creditCard.setIssueDate(new Date());
    creditCard.setExpirationDate(new Date());
    creditCard.setCustomer(customer);

    creditCardRepository.save(creditCard);

    assertNotNull(creditCard.getCreditCardId());
  }

  @Test
  public void getCustomerCreditCards() {
    Customer customer = customerRepository.findOne(1);
    assertThat(customer.getCreditCards().size(), is(2));
  }

  @Test
  public void saveCustomerAndAccountAndCreditCard() throws EntityNotFoundException {
    Customer customer = createCustomer();
    customer = customerRepository.save(customer);

    CreditCard creditCard = createCreditCard();
    creditCard.setCustomer(customer);
    creditCardRepository.save(creditCard);

    Account account = createAccount();
    account.setCustomer(customer);
    accountRepository.save(account);

    Customer customerAfterSave = customerRepository.findOne(customer.getCustomerId());

    assertNotNull(customerAfterSave.getCustomerId());
    assertNotNull(customerAfterSave.getAccount().getAccountId());
    assertThat(customerAfterSave.getCreditCards().size(), is(1));
  }

  @Test
  public void saveEverythingAsNew() {
    Customer customer = createCustomer();

    Account account = createAccount();
    // all subobjects need to have the parent object assigned
    account.setCustomer(customer);
    customer.setAccount(account);

    CreditCard creditCard = createCreditCard();
    creditCard.setCustomer(customer);
    customer.addCreditCard(creditCard);

    customer = customerRepository.save(customer);
    Customer customerAfterSave = customerRepository.findOne(customer.getCustomerId());

    assertNotNull(customerAfterSave);
    assertNotNull(customerAfterSave.getAccount());
    assertNotNull(customerAfterSave.getCreditCards());
  }

  @Test
  public void testDeleteCustomerDeletesAll() {
    Customer customer = createCustomer();
    customerRepository.save(customer);

    Account account = createAccount();
    account.setCustomer(customer);
    accountRepository.save(account);

    CreditCard creditCard = createCreditCard();
    creditCard.setCustomer(customer);
    creditCardRepository.save(creditCard);

    Integer accountId = account.getAccountId();
    Integer creditCardId = creditCard.getCreditCardId();
    Integer customerId = customer.getCustomerId();

    customerRepository.delete(customerId);

    assertNull(customerRepository.findOne(customerId));
    assertNull(accountRepository.findOne(accountId));
    assertNull(creditCardRepository.findOne(creditCardId));
  }

  private Customer createCustomer() {
    Customer customer = new Customer();
    customer.setFirstName("Gonzalo");
    customer.setLastName("Higuain");
    customer.setAddress("Concha Espina, 6");
    return customer;
  }

  private Account createAccount() {
    Account account = new Account();
    account.credit(100);
    return account;
  }

  private CreditCard createCreditCard() {
    CreditCard creditCard = new CreditCard();
    creditCard.setIssueDate(new Date());
    creditCard.setExpirationDate(new Date());
    return creditCard;
  }
}
