/**
 *
 */
package org.gerber.economize.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.PropertiesFactoryBean
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.orm.jpa.vendor.Database

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

import org.gerber.economize.domain.Account
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * @author Michael Gerber
 *
 */
@Configuration
@ComponentScan(basePackages = ["org.gerber.economize.domain",
"org.gerber.economize.hbci4j",
"org.gerber.economize.repositories",
"org.gerber.economize.service"])
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["org.gerber.economize.repositories"], entityManagerFactoryRef = "myEntityManagerFactoryBean")
class SpringConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean myEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(Account.class.getPackage().getName());
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setDatabase(Database.DERBY);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
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
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        return new Properties() {
            {  // Hibernate Specific:
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyTenSevenDialect");
            }
        };
    }

    @Bean(name = "DefaultHbciProperties")
    public Properties getHbciDefaultProperties() {
        return new Properties() {
            {
                setProperty("client.passport.default", "PinTan");
                setProperty("default.hbciversion", "plus");
                setProperty("log.loglevel.default", "0");
                setProperty("kernel.rewriter", "InvalidSegment,WrongStatusSegOrder,WrongSequenceNumbers,MissingMsgRef,HBCIVersion,SigIdLeadingZero,InvalidSuppHBCIVersion,SecTypeTAN,KUmsDelimiters,KUmsEmptyBDateSets");
                setProperty("client.passport.PinTan.init", "1");
                setProperty("client.passport.PinTan.filename", passportName());
                //setProperty("client.passport.PinTan.certfile", "c:\\Programme\\apache-tomcat\\conf\\.test");
                setProperty("client.passport.PinTan.checkcert", "1");
            }
        }
    }

    @Bean(name = 'passportName')
    public String passportName(){
        return "c:\\pintan_hbci4java2"
    }
    @Bean(name = 'bankDataMap')
    public Map<String, String>bankDataMap(){

        def bankDataMap = new HashMap()
        def bankDataProperties = getBankDataProperties()

        for(Object key : bankDataProperties.keys()) {
            if (key instanceof String) {
                Object value = bankDataProperties.getProperty(key)
                if(value != null) {
                    bankDataMap.put(key, value)
                }
            }
        }

        return bankDataMap
    }

    //
    // private methods
    //

    Properties getBankDataProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean()
        propertiesFactoryBean.setLocation(new ClassPathResource("blz.properties"))
        propertiesFactoryBean.afterPropertiesSet();
        propertiesFactoryBean.getObject()
    }

}
