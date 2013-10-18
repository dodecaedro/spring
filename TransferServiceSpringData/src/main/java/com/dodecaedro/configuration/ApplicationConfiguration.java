package com.dodecaedro.configuration;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.dao.support.PersistenceExceptionTranslator;

@Configuration
@EnableJpaRepositories("com.dodecaedro.repository")
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class ApplicationConfiguration {
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
