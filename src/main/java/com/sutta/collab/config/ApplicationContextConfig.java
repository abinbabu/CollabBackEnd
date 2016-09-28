package com.sutta.collab.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sutta.collab.model.Blog;
import com.sutta.collab.model.Event;
import com.sutta.collab.model.UserDetails;


@Configuration
@ComponentScan("com.sutta.collab")
@EnableTransactionManagement
public class ApplicationContextConfig extends WebMvcConfigurerAdapter{
	
	@Bean(name="dataSource")
	public DataSource getOracleDataSource(){
		
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		datasource.setUsername("colabdb");
		datasource.setPassword("root");
		
		Properties connectionProperties = new Properties();
		connectionProperties.put("hibernate.show_sql", "true");
		connectionProperties.put("hibernate.format_sql", "true");
		connectionProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		connectionProperties.put("hibernate.hbm2ddl.auto", "update");
		
		datasource.setConnectionProperties(connectionProperties);
		return datasource;
		
	}
	
	@Autowired
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(UserDetails.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		
		return transactionManager;
	}

	

}
