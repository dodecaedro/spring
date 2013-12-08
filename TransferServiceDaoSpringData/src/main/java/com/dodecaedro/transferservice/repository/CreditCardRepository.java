package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.CreditCard;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = CreditCard.class, idClass = Integer.class)
public interface CreditCardRepository {
  CreditCard findByCreditCardId(Integer creditCardId);
  CreditCard save(CreditCard creditCard);
  CreditCard findCurrentCreditCardFromCustomer(Integer customerId);
}
