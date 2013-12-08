package com.dodecaedro.transferservice.configuration;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("com.dodecaedro.transferservice.repository")
@EnableTransactionManagement
public class TransferServiceDaoConfiguration {
  @Resource
  private DataSource dataSource;

  // these 3 beans are needed only for jpa
  @Bean
  public FactoryBean<EntityManagerFactory> entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(this.dataSource);
    entityManagerFactoryBean.setPackagesToScan("com.dodecaedro.transferservice.data.pojo");
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    return entityManagerFactoryBean;
  }

  @Bean
  public PlatformTransactionManager transactionManager() throws Exception {
    return new JpaTransactionManager(entityManagerFactory().getObject());
  }

  @Bean
  public PersistenceExceptionTranslator persistenceExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }

}
