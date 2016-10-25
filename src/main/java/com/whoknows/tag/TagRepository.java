package com.whoknows.tag;

import com.whoknows.domain.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addTag(Tag tag) {
        jdbcTemplate.update("insert into tag(name, action) values (? , ?) ",
                ps -> {
                    ps.setString(1, tag.getName());
                    ps.setString(2, tag.getAction());
                });
    }

    public void deleteTag(Tag tag) {
        jdbcTemplate.update("delete from tag where id = ? ", ps -> ps.setLong(1, tag.getId()));
    }

    public List<Tag> getTagList() {
        return jdbcTemplate.query("select * from tag",
                (rs, row) -> {
                    Tag tag= new Tag();
                    tag.setId(rs.getLong("id"));
                    tag.setAction(rs.getString("action"));
                    tag.setName(rs.getString("name"));
                    tag.setRank(rs.getLong("rank"));
                    return tag;
                });
    }
}
