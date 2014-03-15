package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.CreditCard;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = CreditCard.class, idClass = Integer.class)
public interface CreditCardRepository {
  CreditCard findByCreditCardId(Integer creditCardId);
  CreditCard save(CreditCard creditCard);
  List<CreditCard> findActiveCreditCardsFromCustomer(Integer customerId);
}
