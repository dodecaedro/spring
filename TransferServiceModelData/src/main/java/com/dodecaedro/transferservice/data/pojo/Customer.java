package com.dodecaedro.transferservice.data.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "customerId")
@XmlRootElement
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "customerSeq", sequenceName = "S_CUSTOMER")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeq")
  @Column(name = "ID")
  private Integer customerId;
  @Column(name = "FIRSTNAME")
  private String firstName;
  @Column(name = "LASTNAME")
  private String lastName;
  @Column(name = "PHONENUMBER")
  private String phoneNumber;
  @Column(name = "ADDRESS")
  private String address;

  // deleting a customer should also delete his account and all credit cards
  @OneToOne(mappedBy = "customer", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
  private Account account;
  @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
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

  @XmlTransient
  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public void addCreditCard(CreditCard creditCard) {
    if (this.creditCards == null) {
      this.creditCards = new HashSet<>();
    }
    creditCards.add(creditCard);
  }

  @XmlTransient
  public Set<CreditCard> getCreditCards() {
    return creditCards;
  }

  public void setCreditCards(Set<CreditCard> creditCards) {
    this.creditCards = creditCards;
  }

  @Override
  public boolean equals(Object anotherObject) {
    if (!(anotherObject instanceof Customer)) {
      return false;
    }
    Customer anotherCustomer = (Customer) anotherObject;
    return this == anotherCustomer || this.customerId.equals(anotherCustomer.getCustomerId());
  }

  @Override
  public int hashCode() {
    if (this.customerId == null) {
      return 0;
    }
    return 17 * (this.customerId ^ (this.customerId >>> 16));
  }
}
