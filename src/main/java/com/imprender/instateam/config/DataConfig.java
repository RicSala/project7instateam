package com.imprender.instateam.config;


import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration //--> So Spring takes it at boottime
@PropertySource("application.properties")
public class DataConfig {

    //so Spring knows where to find the properties
    @Autowired
    private Environment environment;

    //Spring bean configured on boot and available throughout our app
    //Spring equivalnet to what or hibernate session factory was
    //so Spring is able to manage hibernate sessions in the Spring container app context instead of directly configuring a sesion factory

    //Actually, this is just a field of type: LocalSerssionFactoryBean, right? We are just doing two things in one step:
    // declaring the bean and configuring it.
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {

        //Locate the configuration file for hibernate
        Resource config = new ClassPathResource("hibernate.cfg.xml");

        //Create a session factory bean that will manage all our beans
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        //And configure it using the file that we referenced before
        localSessionFactoryBean.setConfigLocation(config);

        //Look for any JPA annotated entities on boot (among those that are located in this package)
        localSessionFactoryBean.setPackagesToScan(environment.getProperty("entity.package"));

        //Configure the data source for the factory bean (that at the same time is a bean itself)
        localSessionFactoryBean.setDataSource(dataSource());

        return localSessionFactoryBean;
    }

    @Bean
    //BE CAREFULL HERE --> MUST BE PUBLIC
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();

        //Driver class name
        ds.setDriverClassName(environment.getProperty("db.driver"));

        //Set URL
        ds.setUrl(environment.getProperty("db.url"));

        //Set username and password
        ds.setUsername(environment.getProperty("db.username"));
        ds.setPassword(environment.getProperty("db.password"));

        return ds;
    }


}
