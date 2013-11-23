package com.dodecaedro.repository;

import com.dodecaedro.data.pojo.CreditCard;

public interface CreditCardRepository {
  CreditCard findByCreditCardId(Integer creditCardId);
  CreditCard save(CreditCard creditCard);
  CreditCard findCurrentCreditCardFromCustomer(Integer customerId);
}
