package br.com.phc.brasileiraoapi.confing;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class dbconfig {
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.url=}")
	private String url;
	
	@Value("${spring.datasource.username=}")
	private String username;
	
	@Value("${spring.datasource.password=}")
	private String password;
	
	@Value("${spring.datasource.hikari.pool-name=}")
	private String poolName;
	
	@Value("${spring.datasource.hikari.minimum-idle}")
	private String minimumIdle;
	
	@Value("${spring.datasource.hikari.maximum-pool-size=}")
	private Integer maximumPollSize;
	
	@Value("${spring.datasource.hikari.connection-timeout=}")
	private long conectionTimeout;
	
	@Value("${spring.datasource.hikari.idle-timeout=}")
	private long idleTimeout;
	
	@Value("${spring.datasource.hikari.max-lifetime=}")
	private long maxlifetime;

	@Bean
	public DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driverClassName);
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		config.setPoolName(poolName);
		config.setMinimumIdle(5);
		config.setMaximumPoolSize(10);
		config.setConnectionTimeout(conectionTimeout);
		config.setIdleTimeout(idleTimeout);
		config.setMaxLifetime(28800000);
		return new HikariDataSource(config);
	}


}

