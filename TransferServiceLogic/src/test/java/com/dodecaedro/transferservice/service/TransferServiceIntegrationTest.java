package com.dodecaedro.transferservice.service;

import com.dodecaedro.transferservice.data.exception.NotEnoughFundsException;
import com.dodecaedro.transferservice.data.pojo.Account;
import com.dodecaedro.transferservice.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml", "/test-infraestructure-config.xml"})
public class TransferServiceIntegrationTest {
  @Resource
  private TransferService transferService;
  // primary is jpa account repository
  @Resource
  private AccountRepository accountRepository;

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

  @Test
  public void payWithCreditCardTest() throws NotEnoughFundsException {
    transferService.payWithCreditCard(1, 50);
  }

}
