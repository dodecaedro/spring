package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.Account;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryDefinition(domainClass = Account.class, idClass = Integer.class)
@Transactional
public interface AccountRepository {
  Account findOne(Integer accountId);
  Account save(Account account);
  List<Account> findAll();
}
