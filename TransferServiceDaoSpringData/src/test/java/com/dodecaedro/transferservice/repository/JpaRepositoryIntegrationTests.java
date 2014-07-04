package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.configuration.TransferServiceDaoConfiguration;
import com.dodecaedro.transferservice.data.pojo.Account;
import com.dodecaedro.transferservice.data.pojo.CreditCard;
import com.dodecaedro.transferservice.data.pojo.Customer;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TransferServiceDaoConfiguration.class)
public class JpaRepositoryIntegrationTests {

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
    account.credit(Account.toMoney(200));

    accountRepository.save(account);

    assertNotNull(account.getAccountId());
  }

  @Test
  public void saveCreditCard() {
    Customer customer = customerRepository.findOne(2);

    CreditCard creditCard = new CreditCard();
    creditCard.setIssueDate(new DateTime());
    creditCard.setExpirationDate(new DateTime());
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
  public void saveCustomerAndAccountAndCreditCard() throws Exception {
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
    // if saved all by saving the customer,
    // the ids in the children objects will not be set

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
    account.credit(Account.toMoney(100));
    return account;
  }

  private CreditCard createCreditCard() {
    CreditCard creditCard = new CreditCard();
    creditCard.setIssueDate(new DateTime());
    creditCard.setExpirationDate(new DateTime());
    return creditCard;
  }
}
