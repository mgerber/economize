/**
 * 
 */
package org.gerber.economize.configuration

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Michael Gerber
 *
 */
@Configuration
@ComponentScan(basePackages = ["org.gerber.economize" ])
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["org.gerber.economize.repositories"], entityManagerFactoryRef="myEntityManagerFactoryBean")
class SpringConfiguration
{
	@Bean
	public LocalContainerEntityManagerFactoryBean myEntityManagerFactoryBean()
	{
	   LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	   em.setDataSource(dataSource());
	   em.setPackagesToScan(org.gerber.economize.domain.Account.class.getPackage().getName());
	   JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	   em.setJpaVendorAdapter(vendorAdapter);
	   em.setJpaProperties(additionalProperties());
  
	   return em;
	}
  
	@Bean
	public DataSource dataSource()
	{
	   DriverManagerDataSource dataSource = new DriverManagerDataSource();
	   dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
	   dataSource.setUrl("jdbc:derby:target/database/message;create=true");
	   dataSource.setUsername("app");
	   dataSource.setPassword("app");
	   /*
	   dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	   dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jpa");
	   dataSource.setUsername( "tutorialuser" );
	   dataSource.setPassword( "tutorialmy5ql" );
	   */
	   return dataSource;
	}
  
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
	{
	   JpaTransactionManager transactionManager = new JpaTransactionManager();
	   transactionManager.setEntityManagerFactory(emf);
  
	   return transactionManager;
	}
  
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
	{
	   return new PersistenceExceptionTranslationPostProcessor();
	}
	Properties additionalProperties()
	{
	   return new Properties() {
		  {  // Hibernate Specific:
			 setProperty("hibernate.hbm2ddl.auto", "create-drop");
			 setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
		  }
	   };
	}
}
