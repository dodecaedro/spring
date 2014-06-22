package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.Transfer;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@NoRepositoryBean
public class TransferRepositoryStub implements TransferRepository {
  private AtomicInteger ID = new AtomicInteger(0);
  Map<Integer, Transfer> transfers = new TreeMap<>();

  @Override
  public void save(Transfer transfer) {
    transfers.put(ID.incrementAndGet(), transfer);
  }

  @Override
  public List<Transfer> findAll() {
    return new ArrayList<>(transfers.values());
  }

  @Override
  public Transfer findOne(Integer transferId) {
    return transfers.get(transferId);
  }
}
