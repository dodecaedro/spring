package com.dodecaedro.transferservice.service;

import com.dodecaedro.transferservice.data.exception.NotEnoughFundsException;
import com.dodecaedro.transferservice.data.pojo.Account;
import com.dodecaedro.transferservice.data.pojo.CreditCard;
import com.dodecaedro.transferservice.data.pojo.Customer;
import com.dodecaedro.transferservice.repository.AccountRepository;
import com.dodecaedro.transferservice.repository.CreditCardRepository;
import com.dodecaedro.transferservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

@Service
public class TransferServiceImpl implements TransferService {
  @Inject
  @Named("accountRepository")
  private AccountRepository accountRepository;
  @Inject
  private CustomerRepository customerRepository;
  @Inject
  private CreditCardRepository creditCardRepository;

  // used in tests to manually inject stub
  public void setAccountRepository(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  public void setCustomerRepository(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public void setCreditCardRepository(CreditCardRepository creditCardRepository) {
    this.creditCardRepository = creditCardRepository;
  }

  @Override
  public void transferBetweenAccounts(int accountOriginId, int accountTargetId, int amount) throws NotEnoughFundsException {
    Account accountOrigin = accountRepository.findOne(accountOriginId);
    Account accountTarget = accountRepository.findOne(accountTargetId);

    if (accountOrigin.getBalance() < amount) {
      throw new NotEnoughFundsException("Account: " + accountOriginId + " does not have enough funds for the transfer");
    }

    accountOrigin.debit(amount);
    accountTarget.credit(amount);

    accountRepository.save(accountOrigin);
    accountRepository.save(accountTarget);
  }


  @Override
  public void transferBetweemCustomers(int sourceCustomerId, int targetCustomerId, int amount) throws NotEnoughFundsException {
    Customer sourceCustomer = customerRepository.findOne(sourceCustomerId);
    Customer targetCustomer = customerRepository.findOne(targetCustomerId);

    if (sourceCustomer.getAccount().getBalance() < amount) {
      throw new NotEnoughFundsException("Customer: " + sourceCustomerId + " does not have enough funds for the transfer");
    }

    sourceCustomer.getAccount().debit(amount);
    targetCustomer.getAccount().credit(amount);

    customerRepository.save(sourceCustomer);
    customerRepository.save(targetCustomer);
  }

  @Override
  public void payWithCreditCard(Integer creditCardId, int amount) throws NotEnoughFundsException {
    CreditCard creditCard = creditCardRepository.findOne(creditCardId);
    Customer customer = creditCard.getCustomer();
    Account account = customer.getAccount();

    if (account.getBalance() < amount) {
      throw new NotEnoughFundsException("Account: " + account.getAccountId() + " does not have enough funds for the payment");
    }

    account.debit(amount);
    accountRepository.save(account);
  }

}
