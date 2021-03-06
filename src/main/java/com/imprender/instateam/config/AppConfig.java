package com.imprender.instateam.config;


import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

//Todo: what @Configuration does??

@Configuration
@PropertySource("application.properties")
public class AppConfig {

    @Autowired
    private Environment env;


    //Todo: wth is this?
    @Bean
    public Hashids hashids() {
        return new Hashids(env.getProperty("giflib.hash.salt"), 8);
    }


}
