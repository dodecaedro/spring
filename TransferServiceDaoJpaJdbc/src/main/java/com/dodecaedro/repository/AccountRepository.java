package com.dodecaedro.repository;

import com.dodecaedro.data.pojo.Account;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountRepository {
  Account findByAccountId(Integer accountId);
  Account save(Account account);
}
