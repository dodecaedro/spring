package com.dodecaedro.transferservice.repository.jdbc;

import com.dodecaedro.transferservice.data.pojo.CreditCard;
import com.dodecaedro.transferservice.repository.CreditCardRepository;

/**
 * User: dodecaedro
 * Date: 11/23/13 * Time: 8:43 PM
 */
public class JdbcCreditCardRepository implements CreditCardRepository {
  @Override
  public CreditCard findByCreditCardId(Integer creditCardId) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public CreditCard save(CreditCard creditCard) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public CreditCard findCurrentCreditCardFromCustomer(Integer customerId) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
