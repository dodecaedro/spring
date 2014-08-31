package com.dodecaedro.transferservice.data.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

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
  private DateTime issueDate;

  @Column(name = "EXPIRATION_DATE")
  private DateTime expirationDate;

  @ManyToOne
  @JoinColumn(name = "ACCOUNT_ID")
  private Account account;

  public Integer getCreditCardId() {
    return creditCardId;
  }

  public void setCreditCardId(Integer creditCardId) {
    this.creditCardId = creditCardId;
  }

  public DateTime getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(DateTime issueDate) {
    this.issueDate = issueDate;
  }

  public DateTime getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(DateTime expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
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
