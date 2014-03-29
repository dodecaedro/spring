package com.dodecaedro.transferservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.support.ClasspathScanningPersistenceUnitPostProcessor;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("com.dodecaedro.transferservice.repository")
@EnableTransactionManagement
public class TransferServiceDaoConfiguration {
  @Inject
  private DataSource dataSource;

  // these 3 beans are needed only for jpa
  @Bean
  public AbstractEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(this.dataSource);
    entityManagerFactoryBean.setPackagesToScan("com.dodecaedro.transferservice.data.pojo");
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.setPersistenceUnitPostProcessors(postProcessor());
    entityManagerFactoryBean.setPersistenceUnitName("TransferServicePU");
    return entityManagerFactoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) throws Exception {
    return new JpaTransactionManager(emf);
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslator() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  private ClasspathScanningPersistenceUnitPostProcessor postProcessor() {
    ClasspathScanningPersistenceUnitPostProcessor processor =
            new ClasspathScanningPersistenceUnitPostProcessor("com.dodecaedro.transferservice.jpql");
    processor.setResourceLoader(new DefaultResourceLoader());
    processor.setMappingFileNamePattern("**/*-orm.xml");
    return processor;
  }

}
