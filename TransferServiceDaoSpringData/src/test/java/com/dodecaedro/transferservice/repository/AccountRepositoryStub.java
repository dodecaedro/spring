package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.Account;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Map;
import java.util.TreeMap;

// notice this is not annotated. it is manually injected
@NoRepositoryBean
public class AccountRepositoryStub implements AccountRepository {
  private Map<Integer, Account> memoryAccounts;

  public AccountRepositoryStub() {
    Account account1 = new Account();
    account1.setAccountId(1);
    account1.credit(150);

    Account account2 = new Account();
    account2.setAccountId(2);
    account2.credit(100);

    Account account3 = new Account();
    account3.setAccountId(3);
    account3.credit(250);

    memoryAccounts = new TreeMap<>();
    memoryAccounts.put(1, account1);
    memoryAccounts.put(2, account2);
    memoryAccounts.put(3, account3);
  }

  @Override
  public Account findOne(Integer id) {
    return memoryAccounts.get(id);
  }

  @Override
  public Account save(Account account) {
    memoryAccounts.put(account.getAccountId(), account);
    return account;
  }

}
