package com.dodecaedro.transferservice.service;

import com.dodecaedro.transferservice.configuration.TransferServiceConfiguration;
import com.dodecaedro.transferservice.data.exception.NotEnoughFundsException;
import com.dodecaedro.transferservice.data.pojo.Account;
import com.dodecaedro.transferservice.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TransferServiceConfiguration.class)
public class TransferServiceIT {
  @Inject
  private TransferService transferService;
  // primary is jpa account repository
  @Inject
  @Named("accountRepository")
  private AccountRepository accountRepository;

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

  @Test
  public void payWithCreditCardTest() throws NotEnoughFundsException {
    transferService.payWithCreditCard(1, 50);
  }

}
