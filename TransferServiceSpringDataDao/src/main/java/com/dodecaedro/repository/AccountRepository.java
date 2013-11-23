package com.dodecaedro.repository;

import com.dodecaedro.data.pojo.Account;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

@RepositoryDefinition(domainClass = Account.class, idClass = Integer.class)
@Transactional
public interface AccountRepository {
  Account findByAccountId(Integer accountId);
  Account save(Account account);
}
