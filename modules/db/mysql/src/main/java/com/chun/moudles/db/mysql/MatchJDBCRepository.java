package com.chun.moudles.db.mysql;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class MatchJDBCRepository {
    private final JdbcTemplate jdbcTemplate;

}
