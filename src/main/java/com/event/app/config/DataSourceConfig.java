package com.event.app.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.event")
@PropertySource("classpath:application.properties")
public class DataSourceConfig implements WebMvcConfigurer {

  @Autowired
  Environment environment;

  private final String URL = "url";
  private final String USER = "username";
  private final String DRIVER = "driver";
  private final String PASSWORD = "password";

  private final long MAX_AGE_SECS = 3600;

  @Bean
  DataSource dataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

    driverManagerDataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/");
    driverManagerDataSource.setUsername("postgres");
    driverManagerDataSource.setPassword("password");
    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");

    return driverManagerDataSource;
  }
}

