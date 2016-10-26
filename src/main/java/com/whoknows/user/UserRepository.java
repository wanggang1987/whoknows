package com.whoknows.user;

import com.whoknows.domain.Topic;
import com.whoknows.domain.User;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PasswordEncoder encoder;
	
	public User getUserInfo(Long id) {
		return jdbcTemplate.query("select * from user where id = ? limit 1",
				ps -> ps.setLong(1, id),
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
				}).stream().findAny().orElse(null);
	}

	public List<Topic> getUserTopic(Long id) {
		return jdbcTemplate.query("select * from topic where user_id = ? ",
				ps -> ps.setLong(1, id),
				(rs,row)->{
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
	
	public void createUser(User user) {
		jdbcTemplate.update("insert into user(email,phone,e_pass) values (?, ?, ?)",
			ps -> {
				ps.setString(1, user.getEmail());
				ps.setString(2, "");
				ps.setString(3, encoder.encode(user.getPasswd()));
			});
	}

}
