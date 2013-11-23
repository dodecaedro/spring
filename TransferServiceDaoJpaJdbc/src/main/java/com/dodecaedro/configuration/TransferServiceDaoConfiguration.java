package com.dodecaedro.configuration;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * User: dodecadro
 * Date: 11/23/13 - Time: 8:39 PM
 */
@Configuration
@EnableTransactionManagement
public class TransferServiceDaoConfiguration {
  @Resource
  private DataSource dataSource;

  // these 3 beans are needed only for jpa
  @Bean
  public FactoryBean<EntityManagerFactory> entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(this.dataSource);
    entityManagerFactoryBean.setPackagesToScan("com.dodecaedro.data.pojo");
    entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
    return entityManagerFactoryBean;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    jpaVendorAdapter.setGenerateDdl(true);
    jpaVendorAdapter.setShowSql(false);
    return jpaVendorAdapter;
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
