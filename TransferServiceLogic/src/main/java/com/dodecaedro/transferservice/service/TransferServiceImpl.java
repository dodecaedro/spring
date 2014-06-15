package com.dodecaedro.transferservice.service;

import com.dodecaedro.transferservice.data.exception.NotEnoughFundsException;
import com.dodecaedro.transferservice.data.pojo.Account;
import com.dodecaedro.transferservice.data.pojo.CreditCard;
import com.dodecaedro.transferservice.data.pojo.Customer;
import com.dodecaedro.transferservice.data.pojo.Transfer;
import com.dodecaedro.transferservice.repository.AccountRepository;
import com.dodecaedro.transferservice.repository.CreditCardRepository;
import com.dodecaedro.transferservice.repository.CustomerRepository;
import com.dodecaedro.transferservice.repository.TransferRepository;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {
  @Inject
  @Named("accountRepository")
  private AccountRepository accountRepository;
  @Inject
  private CustomerRepository customerRepository;
  @Inject
  private CreditCardRepository creditCardRepository;
  @Inject
  @Named("transferRepository")
  private TransferRepository transferRepository;

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

  public void setTransferRepository(TransferRepository transferRepository) {
    this.transferRepository = transferRepository;
  }

  @Override
  public void transferBetweenAccounts(int accountOriginId, int accountTargetId, long amount) throws NotEnoughFundsException {
    Account accountOrigin = accountRepository.findOne(accountOriginId);
    Account accountTarget = accountRepository.findOne(accountTargetId);

    transferFunds(accountOrigin, accountTarget, amount);
  }


  @Override
  public void transferBetweemCustomers(int sourceCustomerId, int targetCustomerId, long amount) throws NotEnoughFundsException {
    Customer sourceCustomer = customerRepository.findOne(sourceCustomerId);
    Customer targetCustomer = customerRepository.findOne(targetCustomerId);

    transferFunds(sourceCustomer.getAccount(), targetCustomer.getAccount(), amount);
  }

  private void transferFunds(Account accountOrigin, Account accountTarget, long amount) throws NotEnoughFundsException {
    if (accountOrigin.getBalance() < amount) {
      throw new NotEnoughFundsException("Account: " + accountOrigin.getAccountId() + " does not have enough funds for the transfer");
    }

    accountOrigin.debit(amount);
    accountTarget.credit(amount);

    Transfer transfer = new Transfer();
    transfer.setAccountOrigin(accountOrigin);
    transfer.setAccountTarget(accountTarget);
    transfer.setTransferDate(DateTime.now());

    accountRepository.save(accountOrigin);
    accountRepository.save(accountTarget);
    transferRepository.save(transfer);
  }

  @Override
  public void payWithCreditCard(Integer creditCardId, long amount) throws NotEnoughFundsException {
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
