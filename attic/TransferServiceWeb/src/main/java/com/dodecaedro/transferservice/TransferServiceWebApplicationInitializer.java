package com.dodecaedro.transferservice;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created by juan on 05/04/14.
 */
public class TransferServiceWebApplicationInitializer implements WebApplicationInitializer {
  @Override
  public void onStartup(ServletContext container) {
    XmlWebApplicationContext appContext = new XmlWebApplicationContext();
    appContext.setConfigLocations(new String[]{"/WEB-INF/spring/dispatcher-config.xml","classpath:infrastructure-config.xml"});

    ServletRegistration.Dynamic dispatcher =
            container.addServlet("TransferServiceDispatcher", new DispatcherServlet(appContext));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");
  }
}
