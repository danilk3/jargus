package org.jargus.database.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author Kotelnikov D.M.
 */
public class MetricsRepositoryImpl implements MetricsRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public MetricsRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
