package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.models.User;

import javax.sql.DataSource;
import java.util.Properties;


    @Configuration
    @PropertySource("classpath:db.properties")
    @EnableTransactionManagement
    @ComponentScan("web")
    public class HiberConfig {

        @Autowired
        private Environment env;

        @Bean
        public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        Properties props=new Properties();
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.put("hibernate.hbm2ddl.import_files", env.getProperty("hibernate.hbm2ddl.import_files"));
        props.put("hibernate.dialect.MySQLDialect", env.getProperty("hibernate.dialect.MySQLDialect"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(User.class);
        return factoryBean;
    }

        @Bean
        public DataSource getDataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(env.getProperty("db.driver"));
            dataSource.setUrl(env.getProperty("db.url"));
            dataSource.setUsername(env.getProperty("db.username"));
            dataSource.setPassword(env.getProperty("db.password"));
            return dataSource;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(getDataSource());
            em.setPackagesToScan("web.models");

            em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            em.setJpaProperties(additionalProperties());
            return em;
        }

        @Bean
        public PlatformTransactionManager getTransactionManager() {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
            return transactionManager;
        }

        Properties additionalProperties() {
            Properties properties = new Properties();
            properties.setProperty("hibernate.dialect.MySQLDialect", env.getProperty("hibernate.dialect.MySQLDialect"));
            properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
            properties.setProperty("hibernate.hbm2ddl.import_files", env.getProperty("hibernate.hbm2ddl.import_files"));
            return properties;
        }
    }


