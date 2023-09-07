package com.sandro.helloboot;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class HelloRepositoryJdbc implements HelloRepository {

    private final JdbcTemplate jdbcTemplate;
    @Override
    public Hello findHello(String name) {
        try {
            return jdbcTemplate.queryForObject("select * from hello where name = ?",
                    (rs, rowNum) -> new Hello(rs.getString("name"), rs.getInt("count")),
                    name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void increaseCount(String name) {
        Hello hello = findHello(name);
        if (hello == null)
            jdbcTemplate.update("insert into hello values (?, ?)", name, 1);
        else
            jdbcTemplate.update("update hello set count = ? where name = ?;", hello.getCount() + 1, name);
    }

}
