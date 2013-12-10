package com.dodecaedro.transferservice.repository.jpa;

import com.dodecaedro.transferservice.data.pojo.Account;
import com.dodecaedro.transferservice.repository.AccountRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@Repository
@Primary
public class JpaAccountRepository implements AccountRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Account findByAccountId(Integer accountId) throws EntityNotFoundException {
    Account account = entityManager.find(Account.class, accountId);
    if (account == null) {
      throw new EntityNotFoundException("Account with id: " + accountId + " not found.");
    }

    return account;
  }

  @Transactional
  @Override
  public Account save(Account account) {
      /* tested: if not marked as transactional, changes will not be
    	 * immediately written to the db, but will stay in memory only */
    return entityManager.merge(account);
  }

}
