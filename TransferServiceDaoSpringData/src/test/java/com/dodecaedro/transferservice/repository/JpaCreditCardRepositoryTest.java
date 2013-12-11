package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.CreditCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml", "/test-infraestructure-config.xml"})
public class JpaCreditCardRepositoryTest {

  @Inject
  private CreditCardRepository creditCardRepository;

  @Test
  public void findByCurrentCardTest() {
    CreditCard creditCard = creditCardRepository.findByCreditCardId(1);
    assertNotNull(creditCard);
  }
}