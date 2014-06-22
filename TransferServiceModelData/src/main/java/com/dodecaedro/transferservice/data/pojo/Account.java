package com.dodecaedro.transferservice.data.pojo;

import com.dodecaedro.transferservice.data.serializers.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "accountId")
@XmlRootElement
@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "accountSeq", sequenceName = "S_ACCOUNT")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSeq")
  @Column(name = "ID")
  private Integer accountId;

  @Column(name = "BALANCE")
  @JsonSerialize(using = MoneySerializer.class)
  private Money balance = Money.zero(CurrencyUnit.EUR);

  @OneToOne
  @JoinColumn(name = "CUSTOMER_ID", nullable = false)
  private Customer customer;

  /*
   * with memory repository, successive calls to the repository would always
   * return the same instance of the object
   */
  public Account(Account copy) {
    this.accountId = copy.accountId;
    this.balance = copy.balance;
  }

  public Account() {
  }

  public void debit(Money amount) {
    this.balance = this.balance.minus(amount);
  }

  public void credit(Money amount) {
    this.balance = this.balance.plus(amount);
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public Money getBalance() {
    return balance;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public boolean equals(Object anotherObject) {
    if (!(anotherObject instanceof Account)) {
      return false;
    }
    Account anotherAccount = (Account) anotherObject;
    return this == anotherAccount || this.accountId.equals(anotherAccount.getAccountId());
  }

  @Override
  public int hashCode() {
    if (this.accountId == null) {
      return 0;
    }
    return 17 * (this.accountId ^ (this.accountId >>> 16));
  }

  public static Money toMoney(long amount) {
    return Money.ofMajor(CurrencyUnit.EUR, amount);
  }
}
