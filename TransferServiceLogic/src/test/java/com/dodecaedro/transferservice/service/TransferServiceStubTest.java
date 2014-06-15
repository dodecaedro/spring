package com.dodecaedro.transferservice.service;

import com.dodecaedro.transferservice.data.exception.NotEnoughFundsException;
import com.dodecaedro.transferservice.data.pojo.Account;
import com.dodecaedro.transferservice.repository.AccountRepository;
import com.dodecaedro.transferservice.repository.AccountRepositoryStub;
import com.dodecaedro.transferservice.repository.TransferRepositoryStub;
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
    transferService.setTransferRepository(new TransferRepositoryStub());
  }

  @Test
  public void transferFunds() throws EntityNotFoundException, NotEnoughFundsException {
    Account account1Origin = new Account(accountRepository.findOne(1));
    Account account2Origin = new Account(accountRepository.findOne(2));

    transferService.transferBetweenAccounts(account1Origin.getAccountId(),
            account2Origin.getAccountId(), 75);

    Account account1Updated = accountRepository.findOne(1);
    Account account2Updated = accountRepository.findOne(2);

    assertEquals(account1Origin.getBalance().minus(75),
            account1Updated.getBalance());
    assertEquals(account2Origin.getBalance().plus(75),
            account2Updated.getBalance());
  }

}
