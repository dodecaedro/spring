package com.dodecaedro.transferservice.repository.jdbc;

import com.dodecaedro.transferservice.data.pojo.Account;
import com.dodecaedro.transferservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcAccountRepository implements AccountRepository {
  private JdbcTemplate jdbcTemplate;
  private RowMapper<Account> rowMapper;

  @Autowired
  public JdbcAccountRepository(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.rowMapper = new AccountRowMapper();
  }

  @Override
  public Account findByAccountId(Integer accountId) throws EntityNotFoundException {
    String sql = "SELECT * FROM ACCOUNT WHERE ID=?";
    try {
      return jdbcTemplate.queryForObject(sql, rowMapper, accountId);
    } catch (Exception exception) {
      throw new EntityNotFoundException(exception.getMessage());
    }
  }

  @Override
  public Account save(Account account) {
    String countSql = "SELECT COUNT(ID) FROM ACCOUNT WHERE ID=?";
    int count = jdbcTemplate.queryForObject(countSql, Integer.class, account.getAccountId());

    if (count == 0) {
      if (account.getAccountId() == 0) {
        String sql = "INSERT INTO ACCOUNT (BALANCE) VALUES (?)";
        jdbcTemplate.update(sql, account.getBalance());
      } else {
        String sql = "INSERT INTO ACCOUNT (ID, BALANCE) VALUES (?, ?)";
        jdbcTemplate.update(sql, account.getAccountId(),
            account.getBalance());
      }
    } else {
      String sql = "UPDATE ACCOUNT SET BALANCE=? WHERE ID=?";
      jdbcTemplate.update(sql, account.getBalance(),
          account.getAccountId());

    }
    return account;
  }

  private class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int rowIndex)
        throws SQLException {
      int id = resultSet.getInt("ID");
      int balance = resultSet.getInt("BALANCE");
      Account account = new Account();
      account.setAccountId(id);
      account.credit(balance);

      return account;
    }

  }

}
