package com.chun.apps.match.config;

import com.chun.cores.db.mysql.MysqlSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Bean
    public DataSource dataSource(){
        return MysqlSource.createDataSource();
    }
}
