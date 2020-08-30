package com.wap.api.db.mysql.bases;

import com.mysql.cj.xdevapi.SessionImpl;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;

public class MySqlClientFatory {

    public static JdbcTemplate createJdbcTemplate() {
        return new JdbcTemplate();
    }

    public static EntityManager createEntityManager() {
        return new SessionImpl();
    }
}
