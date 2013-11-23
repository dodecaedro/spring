package com.dodecaedro.repository.jdbc;

import com.dodecaedro.data.pojo.Customer;
import com.dodecaedro.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: dodecaedro
 * Date: 11/23/13 * Time: 8:43 PM
 */
public class JdbcCustomerRepository implements CustomerRepository {
  private JdbcTemplate jdbcTemplate;
  private RowMapper<Customer> rowMapper;

  @Autowired
  public JdbcCustomerRepository(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.rowMapper = new CustomerRowMapper();
  }

  @Override
  public Customer findByCustomerId(Integer customerId) {
    String sql = "SELECT * FROM CUSTOMER WHERE ID=?";
    try {
      return jdbcTemplate.queryForObject(sql, rowMapper, customerId);
    } catch (Exception exception) {
      throw new EntityNotFoundException(exception.getMessage());
    }
  }

  @Override
  public Customer save(Customer customer) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  private class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet, int rowIndex) throws SQLException {
      int id = resultSet.getInt("ID");
      String firstName = resultSet.getString("FIRSTNAME");

      Customer customer = new Customer();
      customer.setCustomerId(id);

      return customer;
    }
  }
}
