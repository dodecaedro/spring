package com.dodecaedro.transferservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TransferServiceDaoConfiguration.class)
@EnableAspectJAutoProxy
public class TransferServiceConfiguration {
}
