package com.dodecaedro.transferservice.repository.jpa;

import com.dodecaedro.transferservice.data.pojo.CreditCard;
import com.dodecaedro.transferservice.repository.CreditCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml", "/test-infraestructure-config.xml"})
public class JpaCreditCardRepositoryTest {

  @Resource
  private CreditCardRepository creditCardRepository;

  @Test
  public void findByCurrentCardTest() {
    CreditCard creditCard = creditCardRepository.findByCreditCardId(1);
    assertNotNull(creditCard);
  }
}