package com.dodecaedro.transferservice.service;

import com.dodecaedro.transferservice.repository.AccountRepository;
import com.dodecaedro.transferservice.repository.CreditCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityNotFoundException;

/**
 * Created by juan on 26/04/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml", "/test-infrastructure-config.xml"})
public class AspectTest {
  @Inject
  @Named("accountRepository")
  private AccountRepository accountRepository;
  @Inject
  private CreditCardRepository creditCardRepository;

  @Test(expected = IllegalArgumentException.class)
  public void nullAccount() {
    accountRepository.save(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void returnNull() {
    accountRepository.findOne(99);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullCreditCard() {
    creditCardRepository.save(null);
  }

}
