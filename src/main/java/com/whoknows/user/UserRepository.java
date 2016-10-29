package com.whoknows.user;

import com.whoknows.domain.Role;
import com.whoknows.domain.Topic;
import com.whoknows.domain.User;
import com.whoknows.domain.autoMappingBean.UserRowMapper;
import com.whoknows.wkMessage.password.ResetPasswdRequest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PasswordEncoder encoder;
	
	public User getUserById(Long id) {
		return jdbcTemplate.query("select * from user where id = ? limit 1",
				ps -> ps.setLong(1, id), new UserRowMapper()).stream().findAny().orElse(null);
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

	public boolean validUserByEmailAndPasswd(String email, String oldPasswd) {
		try{
			String ePass = jdbcTemplate.queryForObject("select e_pass from user where email = ? limit 1",
					new Object[]{email}, new SingleColumnRowMapper<String>(String.class));
			return encoder.matches(oldPasswd, ePass);
		}catch(Exception e){
			return false;
		}
	}

	public void resetPasswd(ResetPasswdRequest request) {

		jdbcTemplate.update("update user set e_pass = ? where email = ?",
				ps -> {
					ps.setString(1, encoder.encode(request.getNewPasswd()) );
					ps.setString(2, request.getEmail());
				});
	}
	
	public List<Role> getUserRolesByUserId(Long id) {
		return jdbcTemplate.query("select role.* from role left join user_role on role.id = user_role.role_id where user_role.user_id = ?",
				ps -> ps.setLong(1, id),
				(rs,row)->{
					Role role = new Role();
					role.setId(rs.getLong("id"));
					role.setRole(rs.getString("role"));
					return role;
				});
	}
	
	

}
