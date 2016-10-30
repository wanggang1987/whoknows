package com.whoknows.hot;

import com.whoknows.domain.Topic;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HotDAO {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<HotVip> listHotVip(Integer page, int pageSize) {
		return jdbcTemplate.query("select vip.*, user.first_name, user.last_name, user.picture from vip "
				+ "left join user on user.id = vip.user_id "
				+ "order by vip.rank desc "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setInt(1, pageSize);
					ps.setInt(2, (page - 1) * pageSize);
				},
				(rs, row) -> {
					HotVip vip = new HotVip();
					vip.setName(rs.getString("last_name") + rs.getString("first_name"));
					vip.setPricture(rs.getString("picture"));
					vip.setFollow(100L);
					vip.setUserID(rs.getLong("user_id"));
					vip.setVipID(rs.getLong("id"));
					return vip;
				});
	}
	
	public List<HotTag> listHotTag(Integer page, int pageSize) {
		return jdbcTemplate.query("select * from tag "
				+ "order by rank desc "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setInt(1, pageSize);
					ps.setInt(2, (page - 1) * pageSize);
				},
				(rs, row) -> {
					HotTag tag = new HotTag();
					tag.setPicture(null);
					tag.setTagID(rs.getLong("id"));
					tag.setTagName(rs.getNString("name"));
					tag.setFollow(100L);
					return tag;
				});
	}
	
	public List<Topic> listHotTopic(Integer page, int pageSize) {
		return jdbcTemplate.query("select * from topic "
				+ "order by rank desc "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setInt(1, pageSize);
					ps.setInt(2, (page - 1) * pageSize);
				},
				(rs, row) -> {
					Topic topic = new Topic();
					topic.setId(rs.getLong("id"));
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
