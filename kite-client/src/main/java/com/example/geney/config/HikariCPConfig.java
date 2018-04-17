package com.example.geney.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author zhenyu.guo
 * @date 2018/2/7.
 */
@Configuration
public class HikariCPConfig {

    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String userName;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.max-idle}")
    private Integer maxIdle;
    @Value("${jdbc.min-idle}")
    private Integer minIdle;

    private static final Long IDLE_TIMEOUT = 10 * 60 * 1000L;

    @Bean
    @Primary
    public DataSource initDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(userName);
        config.setPassword(password);
        config.setMinimumIdle(minIdle);
        config.setMaximumPoolSize(maxIdle);
        config.setIdleTimeout(IDLE_TIMEOUT);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }

    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager() {
        return new DataSourceTransactionManager(initDataSource());
    }

}
