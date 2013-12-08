package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.CreditCard;

public interface CreditCardRepository {
  CreditCard findByCreditCardId(Integer creditCardId);
  CreditCard save(CreditCard creditCard);
  CreditCard findCurrentCreditCardFromCustomer(Integer customerId);
}
