package com.rails.demoapp.core.common.generic;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 数据库配置类
 * @author wengshengyuan
 *
 */
@Configuration
public class DataSourceConfig {
	/* static params */
	public static final String CENTER_1_EMF = "primaryEntityManagerFactory";
	public static final String CENTER_2_EMF = "secondaryEntityManagerFactory";
	
	public static final String CENTER_1_TM = "primaryTransactionManager";
	public static final String CENTER_2_TM = "secondaryTransactionManager";
	/* static params */
	
	
	
	/* first data source settings */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.one")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	@Bean(name = CENTER_1_EMF)
	@Primary
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(primaryDataSource())
				.packages("com.rails.demoapp.core.module.tbdsboth", "com.rails.demoapp.core.module.tbdsone")
				.persistenceUnit("center_1")
				.properties(buildPostgresProperties()).build();
	}
	@Bean(name=CENTER_1_TM)
	@Autowired
	public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(primaryEntityManagerFactory(builder).getObject());
	}
	/* first data source settings */
	
	
	
	/* second data source settings */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.two")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	@Bean(name = CENTER_2_EMF)
	public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(secondaryDataSource())
				.packages("com.rails.demoapp.core.module.tbdsboth", "com.rails.demoapp.core.module.tbdstwo")
				.persistenceUnit("center_2")
				.properties(buildMySQLProperties()).build();
	}
	@Bean(name=CENTER_2_TM)
	@Autowired
	public PlatformTransactionManager sencondaryTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(secondaryEntityManagerFactory(builder).getObject());
	}
	/* second data source settings */
	
	
	/* postgres jpa settings */
	@Value("${postgres.jpa.ddl-auto}")
	String postgresDll;
	@Value("${postgres.jpa.dialect}")
	String postgresDialect;
	@Value("${postgres.jpa.show-sql}")
	String postgresShowSql = "false";
	private Map<String, Object> buildPostgresProperties() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.ejb.naming_strategy", ImprovedNamingStrategy.class.getName());
		properties.put("hibernate.hbm2ddl.auto", postgresDll);
		properties.put("hibernate.dialect", postgresDialect);
		properties.put("hibernate.show_sql", postgresShowSql);
		return properties;
	}
	/* postgres jpa settings */
	
	
	
	/* mysql jpa settings */
	@Value("${mysql.jpa.ddl-auto}")
	String mysqlDll;
	@Value("${mysql.jpa.dialect}")
	String mysqlDialect;
	@Value("${mysql.jpa.show-sql}")
	String mysqlShowSql = "false";
	private Map<String, Object> buildMySQLProperties() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.ejb.naming_strategy", ImprovedNamingStrategy.class.getName());
		properties.put("hibernate.hbm2ddl.auto", mysqlDll);
		properties.put("hibernate.dialect", mysqlDialect);
		properties.put("hibernate.show_sql", mysqlShowSql);
		return properties;
	}
	/* mysql jpa settings */
}
