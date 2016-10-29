package com.whoknows.tag;

import com.whoknows.domain.Tag;
import com.whoknows.domain.autoMappingBean.TagRowMapper;
import com.whoknows.utils.CharacterConvert;
import com.whoknows.wkMessage.topic.TopicSelectResponse;

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

	public List<TopicSelectResponse> getTagList() {
		return jdbcTemplate.query("select * from tag", (rs, row) -> {
			TopicSelectResponse tag = new TopicSelectResponse();
			tag.setText(rs.getString("name"));
			tag.setValue(rs.getLong("id"));
			return tag;
		});

	}

	public List<Tag> getTagList(String tagName) {
		
		return jdbcTemplate.query("SELECT * FROM whoknows.tag where name like '%"+CharacterConvert.translateSqlConvert(tagName)+"%' order by rank desc  limit 5", new TagRowMapper());
	}
}
