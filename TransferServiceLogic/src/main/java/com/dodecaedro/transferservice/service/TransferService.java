package com.dodecaedro.transferservice.service;

import com.dodecaedro.transferservice.data.exception.NotEnoughFundsException;

public interface TransferService {
  void transferBetweenAccounts(int accountOriginId, int accountTargetId, long amount) throws NotEnoughFundsException;
  void transferBetweemCustomers(int sourceCustomerId, int targetCustomerId, long amount) throws NotEnoughFundsException;
  void payWithCreditCard(Integer creditCardId, long amount) throws NotEnoughFundsException;
}
