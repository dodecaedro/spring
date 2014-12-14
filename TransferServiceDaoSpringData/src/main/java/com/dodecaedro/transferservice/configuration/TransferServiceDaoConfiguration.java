package com.dodecaedro.transferservice.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EntityScan("com.dodecaedro.transferservice.data.pojo")
@EnableJpaRepositories("com.dodecaedro.transferservice.repository")
@ComponentScan("com.dodecaedro.transferservice")
@EnableTransactionManagement
public class TransferServiceDaoConfiguration {
}
