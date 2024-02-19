package org.jargus.database.configuration;

import org.jargus.database.repository.MetricsRepository;
import org.jargus.database.repository.MetricsRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author Kotelnikov D.M.
 */
@Configuration
public class TsDatabaseConfiguration {

    @Bean
    public DataSource dataSource(DatabaseConfig databaseConfig) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseConfig.driverClassName());
        dataSource.setUrl(databaseConfig.url());
        dataSource.setUsername(databaseConfig.username());
        dataSource.setPassword(databaseConfig.password());
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplateA(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    public MetricsRepository metricsRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MetricsRepositoryImpl(jdbcTemplate);
    }
}
