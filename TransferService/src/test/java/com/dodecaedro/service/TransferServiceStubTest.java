package com.dodecaedro.service;

import com.dodecaedro.data.exception.NotEnoughFundsException;
import com.dodecaedro.data.pojo.Account;
import com.dodecaedro.repository.AccountRepository;
import com.dodecaedro.repository.AccountRepositoryStub;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;

public class TransferServiceStubTest {
  private TransferServiceImpl transferService;
  private AccountRepository accountRepository;

  @Before
  public void setUp() {
    this.transferService = new TransferServiceImpl();
    this.accountRepository = new AccountRepositoryStub();

    transferService.setAccountRepository(accountRepository);
  }

  @Test
  public void transferFunds() throws EntityNotFoundException, NotEnoughFundsException {
    Account account1Origin = new Account(accountRepository.findByAccountId(1));
    Account account2Origin = new Account(accountRepository.findByAccountId(2));

    transferService.transferBetweenAccounts(account1Origin.getAccountId(),
        account2Origin.getAccountId(), 75);

    Account account1Updated = accountRepository.findByAccountId(1);
    Account account2Updated = accountRepository.findByAccountId(2);

    assertEquals(account1Origin.getBalance() - 75,
        account1Updated.getBalance());
    assertEquals(account2Origin.getBalance() + 75,
        account2Updated.getBalance());
  }

}
