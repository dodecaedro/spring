package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.CreditCard;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryDefinition(domainClass = CreditCard.class, idClass = Integer.class)
@Transactional
public interface CreditCardRepository {
  CreditCard findOne(Integer creditCardId);
  CreditCard save(CreditCard creditCard);
  List<CreditCard> findActiveCreditCardsFromCustomer(Integer customerId);
}
