package com.dodecaedro.transferservice.controller;

import com.dodecaedro.transferservice.data.pojo.Customer;
import com.dodecaedro.transferservice.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by juan on 06/09/14.
 */
public class CustomerControllerIntegrationTest {
  private MockMvc mockMvc;

  @InjectMocks
  private CustomerController controller;

  @Mock
  private CustomerRepository repository;

  private static final String GET_CUSTOMER_URL = "/customers/{id}";
  private static final String GET_CUSTOMERS_URL = "/customers";

  @Before
  public void setup() {
    initMocks(this);
    this.mockMvc = standaloneSetup(controller)
            .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
  }

  @Test
  public void thatViewCustomerUsesHttpNotFound() throws Exception {
    when(repository.findOne(any(Integer.class))).thenThrow(new EntityNotFoundException());

    this.mockMvc.perform(
            get(GET_CUSTOMER_URL, "99")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }

  @Test
  public void thatViewCustomerUsesHttpOK() throws Exception {
    when(repository.findOne(any(Integer.class))).thenReturn(new Customer());
    this.mockMvc.perform(
            get(GET_CUSTOMER_URL, "1")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  public void thatViewCustomersUsesHttpOK() throws Exception {
    when(repository.findAll()).thenReturn(new ArrayList<>());
    this.mockMvc.perform(
            get(GET_CUSTOMERS_URL)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
}
