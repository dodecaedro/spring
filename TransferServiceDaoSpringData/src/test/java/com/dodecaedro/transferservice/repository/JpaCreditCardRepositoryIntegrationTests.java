package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.configuration.TransferServiceDaoConfiguration;
import com.dodecaedro.transferservice.data.pojo.CreditCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TransferServiceDaoConfiguration.class)
public class JpaCreditCardRepositoryIntegrationTests {

  @Inject
  private CreditCardRepository creditCardRepository;

  @Test
  public void findByCurrentCardTest() {
    CreditCard creditCard = creditCardRepository.findOne(1);
    assertNotNull(creditCard);
  }

  @Test
  public void findActiveCreditCardsTest() {
    List<CreditCard> creditCards = creditCardRepository.findActiveCreditCardsFromCustomer(4);
    assertThat(creditCards, is(not(empty())));
  }
}