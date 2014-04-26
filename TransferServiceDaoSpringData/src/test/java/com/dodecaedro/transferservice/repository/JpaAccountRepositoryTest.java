package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml", "/test-infrastructure-config.xml"})
public class JpaAccountRepositoryTest {

  @Inject
  @Named("accountRepository")
  private AccountRepository accountRepository;

  @Test
  public void loadAccount() throws EntityNotFoundException {
    Account account = accountRepository.findOne(1);
    assertNotNull(account);
  }

  @Test
  public void loadNonExistingAccount() throws EntityNotFoundException {
    assertNull(accountRepository.findOne(99));
  }

  @DirtiesContext
  @Test
  public void updateAccount() throws EntityNotFoundException {
    Account accountOriginal = accountRepository.findOne(2);
    accountOriginal.debit(50);
    accountOriginal = accountRepository.save(accountOriginal);

    Account accountUpdated = accountRepository.findOne(2);

    assertEquals(accountOriginal.getBalance(), accountUpdated.getBalance());
  }
}
