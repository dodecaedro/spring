package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.configuration.TransferServiceDaoConfiguration;
import com.dodecaedro.transferservice.data.pojo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TransferServiceDaoConfiguration.class)
public class JpaAccountRepositoryIntegrationTests {

  @Inject
  @Named("accountRepository")
  private AccountRepository accountRepository;


  @Test
  public void loadAllAccounts() {
    List<Account> accounts = accountRepository.findAll();
    assertThat(accounts, is(not(empty())));
  }

  @Test
  public void loadAccount() {
    Account account = accountRepository.findOne(1);
    assertNotNull(account);
  }

  @Test
  public void loadNonExistingAccount() {
    assertNull(accountRepository.findOne(99));
  }

  @DirtiesContext
  @Test
  public void updateAccount() {
    Account accountOriginal = accountRepository.findOne(2);
    accountOriginal.debit(Account.toMoney(50));
    accountOriginal = accountRepository.save(accountOriginal);

    Account accountUpdated = accountRepository.findOne(2);

    assertEquals(accountOriginal.getBalance(), accountUpdated.getBalance());
  }
}
