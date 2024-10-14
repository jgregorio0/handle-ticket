package dev.jgregorio.handle.ticket.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "dev.jgregorio.handle.ticket.infrastructure.adapters.out.database")
@EnableTransactionManagement
public class DataJpaConfig {

}
