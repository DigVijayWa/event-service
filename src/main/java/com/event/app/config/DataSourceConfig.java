package com.event.app.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.event")
@PropertySource("classpath:application.properties")
public class DataSourceConfig implements WebMvcConfigurer {

  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.driverClassName}")
  private String driverClassName;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  private final long MAX_AGE_SECS = 3600;

  @Bean
  DataSource dataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

    driverManagerDataSource.setUrl(url);
    driverManagerDataSource.setUsername(username);
    driverManagerDataSource.setPassword(password);
    driverManagerDataSource.setDriverClassName(driverClassName);

    return driverManagerDataSource;
  }
}

