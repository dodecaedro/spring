package com.dodecaedro.transferservice.data.pojo;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TRANSFER")
public class Transfer implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  @SequenceGenerator(name = "transferSeq", sequenceName = "S_TRANSFER")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transferSeq")
  private Integer transferId;
  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "ACCOUNT_ORIGIN_ID", nullable = true)
  private Account accountOrigin;
  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "ACCOUNT_TARGET_ID", nullable = true)
  private Account accountTarget;
  @Column(name = "AMOUNT")
  private long amount;
  @Column(name = "TRANSFER_DATE")
  private DateTime transferDate;

  public Integer getTransferId() {
    return transferId;
  }

  public void setTransferId(Integer transferId) {
    this.transferId = transferId;
  }

  public Account getAccountOrigin() {
    return accountOrigin;
  }

  public void setAccountOrigin(Account accountOrigin) {
    this.accountOrigin = accountOrigin;
  }

  public Account getAccountTarget() {
    return accountTarget;
  }

  public void setAccountTarget(Account accountTarget) {
    this.accountTarget = accountTarget;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public DateTime getTransferDate() {
    return transferDate;
  }

  public void setTransferDate(DateTime transferDate) {
    this.transferDate = transferDate;
  }
}
