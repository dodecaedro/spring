package com.dodecaedro.transferservice;

import com.dodecaedro.transferservice.configuration.TransferServiceConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by juan on 05/04/14.
 */
@SpringBootApplication
@Import(TransferServiceConfiguration.class)
public class TransferServiceWebApplicationInitializer extends SpringBootServletInitializer {
  @Bean
  public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
    return new ServletRegistrationBean(dispatcherServlet, "*.json", "*.xml");
  }
}
