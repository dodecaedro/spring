package com.dodecaedro.transferservice.data.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "creditCardId")
@XmlRootElement
@Entity
@Table(name = "CREDITCARD")
public class CreditCard implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "creditCardSeq", sequenceName = "S_CREDITCARD")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creditCardSeq")
  @Column(name = "ID")
  private Integer creditCardId;

  @Column(name = "ISSUE_DATE")
  private Date issueDate;

  @Column(name = "EXPIRATION_DATE")
  private Date expirationDate;

  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ID")
  private Customer customer;

  public Integer getCreditCardId() {
    return creditCardId;
  }

  public void setCreditCardId(Integer creditCardId) {
    this.creditCardId = creditCardId;
  }

  public Date getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(Date issueDate) {
    this.issueDate = issueDate;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public boolean equals(Object anotherObject) {
    if (!(anotherObject instanceof CreditCard)) {
      return false;
    }
    CreditCard anotherCreditCard = (CreditCard) anotherObject;
    return this == anotherCreditCard || this.creditCardId.equals(anotherCreditCard.getCreditCardId());
  }

  @Override
  public int hashCode() {
    if (this.creditCardId == null) {
      return 0;
    }
    return 17 * (this.creditCardId ^ (this.creditCardId >>> 16));
  }
}
