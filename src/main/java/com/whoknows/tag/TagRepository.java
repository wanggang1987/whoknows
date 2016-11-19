package com.whoknows.tag;

import com.whoknows.domain.Tag;
import com.whoknows.domain.Topic;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addTagRelation(Long topicId, Long tagId) {
		jdbcTemplate.update("insert into tag_topic (topic_id, tag_id) values (?, ?) ",
				ps -> {
					ps.setLong(1, topicId);
					ps.setLong(2, tagId);
				});
	}

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

	public Tag getTag(Long id) {
		return jdbcTemplate.query("select * from tag where id = ? ", ps -> ps.setLong(1, id),
				(rs, row) -> {
					Tag tag = new Tag();
					tag.setId(rs.getLong("id"));
					tag.setName(rs.getString("name"));
					tag.setAction(rs.getString("action"));
					tag.setRank(rs.getLong("rank"));
					return tag;
				}).stream().findAny().orElse(null);
	}

	public List<TagSelect> getTagList() {
		return jdbcTemplate.query("select * from tag", (rs, row) -> {
			TagSelect tag = new TagSelect();
			tag.setText(rs.getString("name"));
			tag.setValue(rs.getLong("id"));
			return tag;
		});

	}

	public List<Tag> getTagList(String tagName) {
		return jdbcTemplate.query("SELECT * FROM whoknows.tag where name like ?"
				+ " order by rank desc  limit 5",
				ps -> {
					ps.setString(1, "%" + tagName + "%");
				},
				(rs, row) -> {
					Tag tag = new Tag();
					tag.setId(rs.getLong("id"));
					tag.setName(rs.getString("name"));
					tag.setAction(rs.getString("action"));
					tag.setRank(rs.getLong("rank"));
					return tag;
				});
	}

	public Integer getTopicByTagCount(Long tagId) {
		return jdbcTemplate.query("SELECT count(1) FROM topic "
				+ "left join tag_topic on tag_topic.topic_id = topic.id "
				+ "where tag_topic.tag_id = ? ",
				ps -> {
					ps.setLong(1, tagId);
				},
				(rs, row) -> {
					return rs.getInt("count(1)");
				}).stream().findAny().orElse(null);
	}

	public List<Topic> getTopicByTag(Long tagId, int page, int pageSize) {
		return jdbcTemplate.query("SELECT topic.* FROM topic "
				+ "left join tag_topic on tag_topic.topic_id = topic.id "
				+ "where tag_topic.tag_id = ? "
				+ "order by topic.rank desc "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setLong(1, tagId);
					ps.setInt(2, pageSize);
					ps.setInt(3, (page - 1) * pageSize);
				},
				(rs, row) -> {
					Topic topic = new Topic();
					topic.setId(rs.getLong("id"));
					topic.setUser_id(rs.getLong("user_id"));
					topic.setAction(rs.getString("action"));
					topic.setTitle(rs.getString("title"));
					topic.setContent(rs.getString("content"));
					topic.setRank(rs.getLong("rank"));
					topic.setCreate_time(rs.getTimestamp("create_time"));
					topic.setUpdate_time(rs.getTimestamp("update_time"));
					return topic;
				});
	}
}
