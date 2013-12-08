package com.dodecaedro.transferservice.data.pojo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="ACCOUNT")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
	private Integer accountId;

	@Column(name="BALANCE")
	private int balance;

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

	public void debit(int amount) {
		this.balance -= amount;
	}

	public void credit(int amount) {
		this.balance += amount;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public int getBalance() {
		return balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}	
}
