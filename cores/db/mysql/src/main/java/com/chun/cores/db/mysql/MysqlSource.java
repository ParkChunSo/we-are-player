package com.chun.cores.db.mysql;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MysqlSource {
    private static final String PROPERTIES_FILE_NAME = "mysql-%s.properties";

    public static DataSource createDataSource() {
        try {
            Properties properties = new Properties();
            InputStream input = MysqlSource.class.getClassLoader().getResourceAsStream(String.format(PROPERTIES_FILE_NAME, System.getenv("--spring.profiles.active")));
            if (input == null) {
                input = new FileInputStream(new File("src/main/resources/mysql-dev.properties"));
            }
            properties.load(input);

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("wap.db.mysql.cores.driver-class-name"));
            dataSource.setUrl(properties.getProperty("wap.db.mysql.cores.url"));
            dataSource.setUsername(properties.getProperty("wap.db.mysql.cores.username"));
            dataSource.setPassword(properties.getProperty("wap.db.mysql.cores.password"));
            dataSource.setInitialSize(Integer.parseInt(properties.getProperty("wap.db.mysql.cores.initial-size")));
            dataSource.setMaxIdle(Integer.parseInt(properties.getProperty("wap.db.mysql.cores.max-idle")));
            dataSource.setMinIdle(Integer.parseInt(properties.getProperty("wap.db.mysql.cores.min-idle")));
            dataSource.setMaxTotal(Integer.parseInt(properties.getProperty("wap.db.mysql.cores.max-total")));
            dataSource.setMaxWaitMillis(Integer.parseInt(properties.getProperty("wap.db.mysql.cores.max-wait-mills")));
            dataSource.setPoolPreparedStatements(true);
            return dataSource;
        } catch (IOException e) {
//            log.error("e => {}", e.toString());
            throw new RuntimeException(e);
        }
    }
}
