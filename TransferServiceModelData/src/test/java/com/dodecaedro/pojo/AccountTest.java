package com.dodecaedro.pojo;

import com.dodecaedro.transferservice.data.pojo.Account;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by jm on 12/8/13.
 */
public class AccountTest {
  @Test
  public void testEqualsItself() {
    Account account1 = new Account();
    account1.setAccountId(1);
    account1.credit(Account.toMoney(1000));

    assertTrue(account1.equals(account1));

    Account account2 = new Account(account1);
    assertTrue(account2.equals(account1));
  }

  @Test
  public void testEqualsOther() {
    Account account1 = new Account();
    account1.setAccountId(1);
    account1.credit(Account.toMoney(1000));

    Account account2 = new Account(account1);
    assertTrue(account2.equals(account1));
  }


  @Test
  public void testNotEquals() {
    Account account1 = new Account();
    account1.setAccountId(1);
    account1.credit(Account.toMoney(1000));

    Account account2 = new Account();
    account2.setAccountId(2);
    account2.credit(Account.toMoney(1000));

    assertFalse(account1.equals(account2));
  }

  @Test
  public void testNotEqualsNull() {
    Account account1 = new Account();
    account1.setAccountId(1);
    account1.credit(Account.toMoney(1000));

    assertFalse(account1.equals(null));
  }

  @Test
  public void testSameHashCodeEqualObjects() {
    Account account1 = new Account();
    account1.setAccountId(1);
    account1.credit(Account.toMoney(1000));

    Account account2 = new Account(account1);

    assertEquals(account1.hashCode(), account2.hashCode());
  }
}
