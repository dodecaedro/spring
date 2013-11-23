package com.dodecaedro.repository.jpa;

import com.dodecaedro.data.pojo.CreditCard;
import com.dodecaedro.repository.CreditCardRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Repository
@Primary
public class JpaCreditCardRepository implements CreditCardRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public CreditCard findByCreditCardId(Integer creditCardId) throws EntityNotFoundException {
    CreditCard creditCard = entityManager.find(CreditCard.class, creditCardId);
    if (creditCard == null) {
      throw new EntityNotFoundException("Credit Card with id: " + creditCardId + " not found.");
    }

    return creditCard;
  }

  @Transactional
  @Override
  public CreditCard save(CreditCard creditCard) {
      /* tested: if not marked as transactional, changes will not be
    	 * immediately written to the db, but will stay in memory only */
    return entityManager.merge(creditCard);
  }

  @Override
  public CreditCard findCurrentCreditCardFromCustomer(Integer customerId) {
    return entityManager.createQuery("select c from CreditCard c where c.expirationDate > CURRENT_DATE and c.customer.customerId = :customerId order by c.issueDate desc", CreditCard.class)
//		return entityManager.createQuery("select c from CreditCard c where c.customer.customerId = :customerId order by c.issueDate desc", CreditCard.class)
//		return entityManager.createQuery("select c from CreditCard c where c.expirationDate > CURRENT_DATE", CreditCard.class)
        .setParameter("customerId", customerId)
        .setMaxResults(1).getSingleResult();
  }

}
