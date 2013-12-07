package com.dodecaedro.data.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
	private Integer customerId;
	@Column(name="FIRSTNAME")
	private String firstName;
	@Column(name="LASTNAME")
	private String lastName;
	@Column(name="PHONENUMBER")
	private String phoneNumber;
	@Column(name="ADDRESS")
	private String address;
	@OneToOne(mappedBy="customer")
	private Account account;
	@OneToMany(mappedBy="customer")
	private Set<CreditCard> creditCards;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer id) {
		this.customerId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
