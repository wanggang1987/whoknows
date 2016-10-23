/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whoknows.topic;

import com.whoknows.domain.Topic;
import com.whoknows.domain.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class TopicRepository {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert topiJdbcInsert;

    @Autowired
    public TopicRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        topiJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("topic").usingGeneratedKeyColumns("id");
    }

    public void postTopic(Topic topic) {
        topiJdbcInsert.executeAndReturnKey(Values.getValseMap(topic));
    }
}
