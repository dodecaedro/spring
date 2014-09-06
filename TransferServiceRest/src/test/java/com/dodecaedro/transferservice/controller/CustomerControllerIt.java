package java.com.dodecaedro.transferservice.controller;

import com.dodecaedro.transferservice.controller.CustomerController;
import com.dodecaedro.transferservice.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by juan on 06/09/14.
 */
public class CustomerControllerIT {
  MockMvc mockMvc;

  @InjectMocks
  CustomerController controller;

  @Mock
  CustomerRepository repository;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    this.mockMvc = standaloneSetup(controller)
            .setMessageConverters(new MappingJackson2HttpMessageConverter(),
                    new Jaxb2RootElementHttpMessageConverter()).build();
  }

  @Test
  public void thatViewCustomersUsesHttpOK() throws Exception {
    when(repository.findOne(any(Integer.class))).thenReturn(
            orderDetailsEvent(key));

    this.mockMvc.perform(
            get("/aggregators/orders/{id}", key.toString())
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
}
