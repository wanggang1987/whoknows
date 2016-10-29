package com.whoknows.hot;

import com.whoknows.domain.Topic;
import com.whoknows.domain.Vip;
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

	private final int pageSize = 5;

	public List<Vip> listHotVip(int page) {
		return jdbcTemplate.query("select * from vip order by rank LIMIT ? OFFSET ?",
				ps -> {
					ps.setInt(1, pageSize);
					ps.setInt(2, (page - 1) * pageSize);
				},
				(rs, row) -> {
					Vip vip = new Vip();
					vip.setId(rs.getLong("id"));
					vip.setAction(rs.getString("action"));
					vip.setPersonal_profile_id(rs.getLong("personal_profile_id"));
					vip.setUser_id(rs.getLong("user_id"));
					vip.setCreate_time(rs.getTimestamp("create_time"));
					vip.setUpdate_time(rs.getTimestamp("update_time"));
					return vip;
				});
	}

	public List<Topic> listHotTopic(int page) {
		return jdbcTemplate.query("select * from topic order by rank limit ? OFFSET ?",
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
