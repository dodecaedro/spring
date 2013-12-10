package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.Account;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountRepository {
  Account findByAccountId(Integer accountId);
  Account save(Account account);
}
