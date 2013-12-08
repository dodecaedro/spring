package com.dodecaedro.transferservice.data.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "accountId")
@Entity
@Table(name="ACCOUNT")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
	private Integer accountId;

	@Column(name="BALANCE")
	private long balance;

	@OneToOne
	@JoinColumn(name="CUSTOMER_ID", nullable=false)
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

	public void debit(long amount) {
		this.balance -= amount;
	}

	public void credit(long amount) {
		this.balance += amount;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public long getBalance() {
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
    Account anotherAccount = (Account)anotherObject;
    return this == anotherAccount || this.accountId.equals(anotherAccount.getAccountId());
  }

  @Override
  public int hashCode() {
    if (this.accountId == null) {
      return 0;
    }

    return 17 * (this.accountId ^ (this.accountId >>> 16));
  }
}
