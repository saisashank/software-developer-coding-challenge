package com.traderev.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "tradeRevEntityManagerFactory", transactionManagerRef = "tradeRevTransactionManager", basePackages = {"com.traderev.repository" })
public class TradeRevConfig {

	@Value("${spring.datasource.hikari.driverClassName}")
	private String dbDriver;

	@Value("${spring.datasource.hikari.jdbcUrl}")
	private String dbUrl;

	@Value("${spring.datasource.hikari.username}")
	private String userName;
	
	@Value("${spring.datasource.hikari.password}")
	private String password;

	@Primary
	@Bean(name = "tradeRevDataSource")
	public DataSource dataSource(@Qualifier("hikariCP") HikariConfig hikariConfig) {
		
		hikariConfig.setJdbcUrl(dbUrl);
		hikariConfig.setUsername(userName);
		hikariConfig.setPassword(password);
		hikariConfig.setDriverClassName(dbDriver);
		return new HikariDataSource(hikariConfig);

	}

	@Primary
	@Bean(name = "tradeRevEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean csEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("tradeRevDataSource") DataSource dataSource) {
		Map<String, String> properties = new HashMap<>();

		return builder.dataSource(dataSource).packages("com.traderev.model", "com.traderev.repository").properties(properties).persistenceUnit("tradeRevApp").build();
	}

	@Bean(name = "hikariCP")
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		return hikariConfig;
	}

	@Primary
	@Bean(name = "tradeRevTransactionManager")
	public PlatformTransactionManager csTransactionManager(
			@Qualifier("tradeRevEntityManagerFactory") EntityManagerFactory csEntityManagerFactory) {

		return new JpaTransactionManager(csEntityManagerFactory);
	}
}
