package com.whoknows.search;

import com.whoknows.domain.Topic;
import com.whoknows.domain.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDAO {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Topic> searchTagByKeyWord(String key, int page, int pageSize, SearchType type) {
		return jdbcTemplate.query("select * from topic "
				+ "where title like ? "
				+ "and id in (select topic_id from tag_topic "
				+ "		where tag_id = ( select id from tag where name = ? ) "
				+ "		) "
				+ "order by ? "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setString(1, "%" + key + "%");
					ps.setString(2, key);
					ps.setString(3, type.name());
					ps.setInt(4, pageSize);
					ps.setInt(5, (page - 1) * pageSize);;
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

	public List<Topic> searchTopicByKeyWord(String key, int page, int pageSize, SearchType type) {
		return jdbcTemplate.query("select * from topic "
				+ "where title like ? "
				+ "order by ? "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setString(1, "%" + key + "%");
					ps.setString(2, type.name());
					ps.setInt(3, pageSize);
					ps.setInt(4, (page - 1) * pageSize);
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

	public List<User> searchUserByKeyWord(String key, int page, int pageSize, SearchType type, boolean vip) {
		return jdbcTemplate.query("select * from user "
				+ "where ( email like ? "
				+ "or phone like ? "
				+ "or first_name like ? "
				+ "or last_name like ? ) "
				+ "and vip = ? "
				+ "order by ? "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setString(1, "%" + key + "%");
					ps.setString(2, "%" + key + "%");
					ps.setString(3, "%" + key + "%");
					ps.setString(4, "%" + key + "%");
					ps.setBoolean(5, vip);
					ps.setString(6, type.name());
					ps.setInt(7, pageSize);
					ps.setInt(8, (page - 1) * pageSize);
				},
				(rs, row) -> {
					User user = new User();
					user.setId(rs.getLong("id"));
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					user.setPasswd(rs.getString("passwd"));
					user.setePass(rs.getString("e_pass"));
					user.setFirstName(rs.getString("first_name"));
					user.setLastName(rs.getString("last_name"));
					user.setCompanyName(rs.getString("company_name"));
					user.setProvince(rs.getString("province"));
					user.setCity(rs.getString("city"));
					user.setAddress(rs.getString("address"));
					user.setCreateTime(rs.getTimestamp("create_time"));
					user.setUpdateTime(rs.getTimestamp("update_time"));
					user.setVip(rs.getBoolean("vip"));
					user.setPicture(rs.getString("picture"));
					user.setEducation(rs.getString("education"));
					user.setSignatureId(rs.getLong("signature_id"));
					user.setTitle(rs.getString("title"));
					user.setAction(rs.getString("action"));
					return user;
				});
	}
}
