package com.event.app.config;

import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

  private final long MAX_AGE_SECS = 3600;
  Logger logger = LogManager.getLogger(DataSourceConfig.class);
  @Value("${spring.datasource.url}")
  private String url;
  @Value("${spring.datasource.driverClassName}")
  private String driverClassName;
  @Value("${spring.datasource.username}")
  private String username;
  @Value("${spring.datasource.password}")
  private String password;

  @Bean
  DataSource dataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

    logger.debug(String
        .format("url: %s, username: %s, password: %s, driverClassName: %s", url, username,
            password, driverClassName));

    driverManagerDataSource.setUrl(url);
    driverManagerDataSource.setUsername(username);
    driverManagerDataSource.setPassword(password);
    driverManagerDataSource.setDriverClassName(driverClassName);

    return driverManagerDataSource;
  }
}

