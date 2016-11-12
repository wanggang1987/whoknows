package com.whoknows.security.autoDao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.whoknows.domain.User;

@Component
public class AuthDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String QUERY_USER_BY_USER_EMAIL = "SELECT * FROM user where email = ?";
	private final String QUERY_ROLES_BY_USER_ID = "select role.role from role left join user_role on role.id = user_role.role_id where user_id = ?";

	public User getUserByEmail(String username) {
		return jdbcTemplate.query(QUERY_USER_BY_USER_EMAIL,
				ps -> ps.setString(1, username),
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
					user.setSignature(rs.getString("signature"));
					user.setTitle(rs.getString("title"));
					user.setAction(rs.getString("action"));
					return user;
				}).stream().findAny().orElse(null);

	}

	public List<String> getRolesByUserId(Long id) {
		List<String> roles = jdbcTemplate.queryForList(QUERY_ROLES_BY_USER_ID, new Object[]{id}, String.class);
		if (roles.size() == 0) {
			roles = Arrays.asList(new String[]{"SITE_USER"});
		}
		return roles;
	}

}
