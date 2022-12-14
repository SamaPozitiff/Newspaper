package com.example.homepagenewspaper;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories("repositories")
@EntityScan({"entities"})
public class ApplicationConfiguration {



    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("Gironimo248");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/newspaper?createDatabaseIfNotExist=true");
        return dataSource;
    }
}
