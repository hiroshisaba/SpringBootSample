package com.example.SpringBootSample.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class HelloRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> findById(String id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * ");
        query.append("FROM employee ");
        query.append("WHERE id = ?");

        Map<String, Object> employee = jdbcTemplate.queryForMap(query.toString(), id);
        return employee;
    }
}
