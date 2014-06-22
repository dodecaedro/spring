package com.dodecaedro.transferservice.repository;

import com.dodecaedro.transferservice.data.pojo.Transfer;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Transfer.class, idClass = Integer.class)
public interface TransferRepository {
  void save(Transfer transfer);
  List<Transfer> findAll();
  Transfer findOne(Integer transferId);
}
