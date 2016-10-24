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

    public void createTopic(Topic topic) {
        topiJdbcInsert.executeAndReturnKey(Values.getValseMap(topic));
    }

    public void updateTopic(Topic topic) {
        jdbcTemplate.update("update topic set user_id = ?, title = ? , content = ? where id = ? ",
                ps -> {
                    ps.setLong(1, topic.getUser_id());
                    ps.setString(2, topic.getTitle());
                    ps.setString(3, topic.getContent());
                    ps.setLong(4, topic.getId());
                });
    }

    public void deleteTopic(Topic topic) {
        jdbcTemplate.update("delete from topic where id = ? ", ps -> ps.setLong(1, topic.getId()));
    }

    public Topic getTopic(Long id) {
        return jdbcTemplate.query("select * from topic where id = ? limit 1",
                ps -> ps.setLong(1, id),
                (rs, row) -> {
                    Topic topic = new Topic();
                    topic.setId(id);
                    topic.setAction(rs.getString("action"));
                    try {
                        topic.setTitle(new String(rs.getBytes("title"), "UTF-8"));
                        topic.setContent(new String(rs.getBytes("content"), "UTF-8"));
                    } catch (Exception e) {
                    }
                    topic.setCreate_time(rs.getTimestamp("create_time"));
                    topic.setUpdate_time(rs.getTimestamp("update_time"));
                    return topic;
                }).stream().findAny().orElse(null);
    }
}
